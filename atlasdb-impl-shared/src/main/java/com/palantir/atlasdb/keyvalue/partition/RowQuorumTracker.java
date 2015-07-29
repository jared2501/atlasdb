package com.palantir.atlasdb.keyvalue.partition;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.UnsignedBytes;
import com.palantir.atlasdb.keyvalue.partition.QuorumParameters.QuorumRequestParameters;

public class RowQuorumTracker<T> {

    private final Map<byte[], Integer> numberOfRemainingSuccessesForSuccess;
    private final Map<byte[], Integer> numberOfRemainingFailuresForFailure;
    private final Map<Future<T>, Set<byte[]>> rowsByReference;
    private boolean failure;

    /*
     * successFactor - minimum number of successes per cell
     */
    RowQuorumTracker(Iterable<byte[]> allRows, QuorumRequestParameters qrp) {
        numberOfRemainingFailuresForFailure = Maps.newTreeMap(UnsignedBytes.lexicographicalComparator());
        numberOfRemainingSuccessesForSuccess = Maps.newTreeMap(UnsignedBytes.lexicographicalComparator());
        rowsByReference = Maps.newHashMap();
        failure = false;
        for (byte[] row : allRows) {
            numberOfRemainingSuccessesForSuccess.put(row, qrp.getSuccessFactor());
            numberOfRemainingFailuresForFailure.put(row, qrp.getFailureFactor());
        }
    }

    public static <V> RowQuorumTracker<V> of(Iterable<byte[]> allRows, QuorumRequestParameters qrp) {
        return new RowQuorumTracker<V>(allRows, qrp);
    }

    public void handleSuccess(Future<T> ref) {
        Preconditions.checkState(failure() == false && success() == false);
        Preconditions.checkState(rowsByReference.containsKey(ref));
        for (byte[] row : rowsByReference.get(ref)) {
            if (numberOfRemainingSuccessesForSuccess.containsKey(row)) {
                int newValue = numberOfRemainingSuccessesForSuccess.get(row) - 1;
                if (newValue == 0) {
                    numberOfRemainingSuccessesForSuccess.remove(row);
                    numberOfRemainingFailuresForFailure.remove(row);
                } else {
                    numberOfRemainingSuccessesForSuccess.put(row, newValue);
                }
            }
        }
        rowsByReference.remove(ref);
    }

    public void handleFailure(Future<T> ref) {
        Preconditions.checkState(failure() == false && success() == false);
        Preconditions.checkArgument(rowsByReference.containsKey(ref));
        for (byte[] row : rowsByReference.get(ref)) {
            if (numberOfRemainingFailuresForFailure.containsKey(row)) {
                int newValue = numberOfRemainingFailuresForFailure.get(row) - 1;
                if (newValue == 0) {
                    failure = true;
                    break;
                } else {
                    numberOfRemainingFailuresForFailure.put(row, newValue);
                }
            }
        }
        rowsByReference.remove(ref);
    }

    public void registerRef(Future<T> ref, Iterable<byte[]> rows) {
        Preconditions.checkState(failure() == false && success() == false);
        Set<byte[]> set = Sets.newTreeSet(UnsignedBytes.lexicographicalComparator());
        for (byte[] row : rows) {
            set.add(row);
        }
        rowsByReference.put(ref, set);
    }

    public void cancel(boolean mayInterruptIfRunning) {
        for (Future<T> f : rowsByReference.keySet()) {
            f.cancel(mayInterruptIfRunning);
        }
    }

    public boolean failure() {
        return failure;
    }

    public boolean success() {
        return !failure() && numberOfRemainingSuccessesForSuccess.isEmpty();
    }

    public boolean finished() {
        return failure() || success();
    }
}