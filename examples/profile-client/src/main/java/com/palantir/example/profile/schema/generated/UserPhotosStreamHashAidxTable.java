package com.palantir.example.profile.schema.generated;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Generated;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Collections2;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.UnsignedBytes;
import com.google.protobuf.InvalidProtocolBufferException;
import com.palantir.atlasdb.compress.CompressionUtils;
import com.palantir.atlasdb.encoding.PtBytes;
import com.palantir.atlasdb.keyvalue.api.Cell;
import com.palantir.atlasdb.keyvalue.api.ColumnSelection;
import com.palantir.atlasdb.keyvalue.api.Prefix;
import com.palantir.atlasdb.keyvalue.api.RangeRequest;
import com.palantir.atlasdb.keyvalue.api.RowResult;
import com.palantir.atlasdb.keyvalue.impl.Cells;
import com.palantir.atlasdb.ptobject.EncodingUtils;
import com.palantir.atlasdb.schema.Namespace;
import com.palantir.atlasdb.table.api.AtlasDbDynamicMutableExpiringTable;
import com.palantir.atlasdb.table.api.AtlasDbDynamicMutablePersistentTable;
import com.palantir.atlasdb.table.api.AtlasDbMutableExpiringTable;
import com.palantir.atlasdb.table.api.AtlasDbMutablePersistentTable;
import com.palantir.atlasdb.table.api.AtlasDbNamedExpiringSet;
import com.palantir.atlasdb.table.api.AtlasDbNamedMutableTable;
import com.palantir.atlasdb.table.api.AtlasDbNamedPersistentSet;
import com.palantir.atlasdb.table.api.ColumnValue;
import com.palantir.atlasdb.table.api.TypedRowResult;
import com.palantir.atlasdb.table.description.ColumnValueDescription.Compression;
import com.palantir.atlasdb.table.description.ValueType;
import com.palantir.atlasdb.table.generation.ColumnValues;
import com.palantir.atlasdb.table.generation.Descending;
import com.palantir.atlasdb.table.generation.NamedColumnValue;
import com.palantir.atlasdb.transaction.api.AtlasDbConstraintCheckingMode;
import com.palantir.atlasdb.transaction.api.ConstraintCheckingTransaction;
import com.palantir.atlasdb.transaction.api.Transaction;
import com.palantir.common.base.AbortingVisitor;
import com.palantir.common.base.AbortingVisitors;
import com.palantir.common.base.BatchingVisitable;
import com.palantir.common.base.BatchingVisitableView;
import com.palantir.common.base.BatchingVisitables;
import com.palantir.common.base.Throwables;
import com.palantir.common.collect.IterableView;
import com.palantir.common.persist.Persistable;
import com.palantir.common.persist.Persistable.Hydrator;
import com.palantir.common.persist.Persistables;
import com.palantir.common.proxy.AsyncProxy;
import com.palantir.util.AssertUtils;
import com.palantir.util.crypto.Sha256Hash;


@Generated("com.palantir.atlasdb.table.description.render.TableRenderer")
public final class UserPhotosStreamHashAidxTable implements
        AtlasDbDynamicMutablePersistentTable<UserPhotosStreamHashAidxTable.UserPhotosStreamHashAidxRow,
                                                UserPhotosStreamHashAidxTable.UserPhotosStreamHashAidxColumn,
                                                UserPhotosStreamHashAidxTable.UserPhotosStreamHashAidxColumnValue,
                                                UserPhotosStreamHashAidxTable.UserPhotosStreamHashAidxRowResult> {
    private final Transaction t;
    private final List<UserPhotosStreamHashAidxTrigger> triggers;
    private final static String rawTableName = "user_photos_stream_hash_aidx";
    private final String tableName;
    private final Namespace namespace;

    static UserPhotosStreamHashAidxTable of(Transaction t, Namespace namespace) {
        return new UserPhotosStreamHashAidxTable(t, namespace, ImmutableList.<UserPhotosStreamHashAidxTrigger>of());
    }

    static UserPhotosStreamHashAidxTable of(Transaction t, Namespace namespace, UserPhotosStreamHashAidxTrigger trigger, UserPhotosStreamHashAidxTrigger... triggers) {
        return new UserPhotosStreamHashAidxTable(t, namespace, ImmutableList.<UserPhotosStreamHashAidxTrigger>builder().add(trigger).add(triggers).build());
    }

    static UserPhotosStreamHashAidxTable of(Transaction t, Namespace namespace, List<UserPhotosStreamHashAidxTrigger> triggers) {
        return new UserPhotosStreamHashAidxTable(t, namespace, triggers);
    }

    private UserPhotosStreamHashAidxTable(Transaction t, Namespace namespace, List<UserPhotosStreamHashAidxTrigger> triggers) {
        this.t = t;
        this.tableName = namespace.getName().isEmpty() ? rawTableName : namespace.getName() + "." + rawTableName;
        this.triggers = triggers;
        this.namespace = namespace;
    }

    public static String getRawTableName() {
        return rawTableName;
    }

    public String getTableName() {
        return tableName;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    /**
     * <pre>
     * UserPhotosStreamHashAidxRow {
     *   {@literal Sha256Hash hash};
     * }
     * </pre>
     */
    public static final class UserPhotosStreamHashAidxRow implements Persistable, Comparable<UserPhotosStreamHashAidxRow> {
        private final Sha256Hash hash;

        public static UserPhotosStreamHashAidxRow of(Sha256Hash hash) {
            return new UserPhotosStreamHashAidxRow(hash);
        }

        private UserPhotosStreamHashAidxRow(Sha256Hash hash) {
            this.hash = hash;
        }

        public Sha256Hash getHash() {
            return hash;
        }

        public static Function<UserPhotosStreamHashAidxRow, Sha256Hash> getHashFun() {
            return new Function<UserPhotosStreamHashAidxRow, Sha256Hash>() {
                @Override
                public Sha256Hash apply(UserPhotosStreamHashAidxRow row) {
                    return row.hash;
                }
            };
        }

        public static Function<Sha256Hash, UserPhotosStreamHashAidxRow> fromHashFun() {
            return new Function<Sha256Hash, UserPhotosStreamHashAidxRow>() {
                @Override
                public UserPhotosStreamHashAidxRow apply(Sha256Hash row) {
                    return UserPhotosStreamHashAidxRow.of(row);
                }
            };
        }

        @Override
        public byte[] persistToBytes() {
            byte[] hashBytes = hash.getBytes();
            return EncodingUtils.add(hashBytes);
        }

        public static final Hydrator<UserPhotosStreamHashAidxRow> BYTES_HYDRATOR = new Hydrator<UserPhotosStreamHashAidxRow>() {
            @Override
            public UserPhotosStreamHashAidxRow hydrateFromBytes(byte[] __input) {
                int __index = 0;
                Sha256Hash hash = new Sha256Hash(EncodingUtils.get32Bytes(__input, __index));
                __index += 32;
                return new UserPhotosStreamHashAidxRow(hash);
            }
        };

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(getClass().getSimpleName())
                .add("hash", hash)
                .toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            UserPhotosStreamHashAidxRow other = (UserPhotosStreamHashAidxRow) obj;
            return Objects.equal(hash, other.hash);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(hash);
        }

        @Override
        public int compareTo(UserPhotosStreamHashAidxRow o) {
            return ComparisonChain.start()
                .compare(this.hash, o.hash)
                .result();
        }
    }

    /**
     * <pre>
     * UserPhotosStreamHashAidxColumn {
     *   {@literal Long streamId};
     * }
     * </pre>
     */
    public static final class UserPhotosStreamHashAidxColumn implements Persistable, Comparable<UserPhotosStreamHashAidxColumn> {
        private final long streamId;

        public static UserPhotosStreamHashAidxColumn of(long streamId) {
            return new UserPhotosStreamHashAidxColumn(streamId);
        }

        private UserPhotosStreamHashAidxColumn(long streamId) {
            this.streamId = streamId;
        }

        public long getStreamId() {
            return streamId;
        }

        public static Function<UserPhotosStreamHashAidxColumn, Long> getStreamIdFun() {
            return new Function<UserPhotosStreamHashAidxColumn, Long>() {
                @Override
                public Long apply(UserPhotosStreamHashAidxColumn row) {
                    return row.streamId;
                }
            };
        }

        public static Function<Long, UserPhotosStreamHashAidxColumn> fromStreamIdFun() {
            return new Function<Long, UserPhotosStreamHashAidxColumn>() {
                @Override
                public UserPhotosStreamHashAidxColumn apply(Long row) {
                    return UserPhotosStreamHashAidxColumn.of(row);
                }
            };
        }

        @Override
        public byte[] persistToBytes() {
            byte[] streamIdBytes = EncodingUtils.encodeUnsignedVarLong(streamId);
            return EncodingUtils.add(streamIdBytes);
        }

        public static final Hydrator<UserPhotosStreamHashAidxColumn> BYTES_HYDRATOR = new Hydrator<UserPhotosStreamHashAidxColumn>() {
            @Override
            public UserPhotosStreamHashAidxColumn hydrateFromBytes(byte[] __input) {
                int __index = 0;
                Long streamId = EncodingUtils.decodeUnsignedVarLong(__input, __index);
                __index += EncodingUtils.sizeOfUnsignedVarLong(streamId);
                return new UserPhotosStreamHashAidxColumn(streamId);
            }
        };

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(getClass().getSimpleName())
                .add("streamId", streamId)
                .toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            UserPhotosStreamHashAidxColumn other = (UserPhotosStreamHashAidxColumn) obj;
            return Objects.equal(streamId, other.streamId);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(streamId);
        }

        @Override
        public int compareTo(UserPhotosStreamHashAidxColumn o) {
            return ComparisonChain.start()
                .compare(this.streamId, o.streamId)
                .result();
        }
    }

    public interface UserPhotosStreamHashAidxTrigger {
        public void putUserPhotosStreamHashAidx(Multimap<UserPhotosStreamHashAidxRow, ? extends UserPhotosStreamHashAidxColumnValue> newRows);
    }

    /**
     * <pre>
     * Column name description {
     *   {@literal Long streamId};
     * }
     * Column value description {
     *   type: Long;
     * }
     * </pre>
     */
    public static final class UserPhotosStreamHashAidxColumnValue implements ColumnValue<Long> {
        private final UserPhotosStreamHashAidxColumn columnName;
        private final Long value;

        public static UserPhotosStreamHashAidxColumnValue of(UserPhotosStreamHashAidxColumn columnName, Long value) {
            return new UserPhotosStreamHashAidxColumnValue(columnName, value);
        }

        private UserPhotosStreamHashAidxColumnValue(UserPhotosStreamHashAidxColumn columnName, Long value) {
            this.columnName = columnName;
            this.value = value;
        }

        public UserPhotosStreamHashAidxColumn getColumnName() {
            return columnName;
        }

        @Override
        public Long getValue() {
            return value;
        }

        @Override
        public byte[] persistColumnName() {
            return columnName.persistToBytes();
        }

        @Override
        public byte[] persistValue() {
            byte[] bytes = EncodingUtils.encodeUnsignedVarLong(value);
            return CompressionUtils.compress(bytes, Compression.NONE);
        }

        public static Long hydrateValue(byte[] bytes) {
            bytes = CompressionUtils.decompress(bytes, Compression.NONE);
            return EncodingUtils.decodeUnsignedVarLong(bytes, 0);
        }

        public static Function<UserPhotosStreamHashAidxColumnValue, UserPhotosStreamHashAidxColumn> getColumnNameFun() {
            return new Function<UserPhotosStreamHashAidxColumnValue, UserPhotosStreamHashAidxColumn>() {
                @Override
                public UserPhotosStreamHashAidxColumn apply(UserPhotosStreamHashAidxColumnValue columnValue) {
                    return columnValue.getColumnName();
                }
            };
        }

        public static Function<UserPhotosStreamHashAidxColumnValue, Long> getValueFun() {
            return new Function<UserPhotosStreamHashAidxColumnValue, Long>() {
                @Override
                public Long apply(UserPhotosStreamHashAidxColumnValue columnValue) {
                    return columnValue.getValue();
                }
            };
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(getClass().getSimpleName())
                .add("ColumnName", this.columnName)
                .add("Value", this.value)
                .toString();
        }
    }

    public static final class UserPhotosStreamHashAidxRowResult implements TypedRowResult {
        private final UserPhotosStreamHashAidxRow rowName;
        private final ImmutableSet<UserPhotosStreamHashAidxColumnValue> columnValues;

        public static UserPhotosStreamHashAidxRowResult of(RowResult<byte[]> rowResult) {
            UserPhotosStreamHashAidxRow rowName = UserPhotosStreamHashAidxRow.BYTES_HYDRATOR.hydrateFromBytes(rowResult.getRowName());
            Set<UserPhotosStreamHashAidxColumnValue> columnValues = Sets.newHashSetWithExpectedSize(rowResult.getColumns().size());
            for (Entry<byte[], byte[]> e : rowResult.getColumns().entrySet()) {
                UserPhotosStreamHashAidxColumn col = UserPhotosStreamHashAidxColumn.BYTES_HYDRATOR.hydrateFromBytes(e.getKey());
                Long value = UserPhotosStreamHashAidxColumnValue.hydrateValue(e.getValue());
                columnValues.add(UserPhotosStreamHashAidxColumnValue.of(col, value));
            }
            return new UserPhotosStreamHashAidxRowResult(rowName, ImmutableSet.copyOf(columnValues));
        }

        private UserPhotosStreamHashAidxRowResult(UserPhotosStreamHashAidxRow rowName, ImmutableSet<UserPhotosStreamHashAidxColumnValue> columnValues) {
            this.rowName = rowName;
            this.columnValues = columnValues;
        }

        @Override
        public UserPhotosStreamHashAidxRow getRowName() {
            return rowName;
        }

        public Set<UserPhotosStreamHashAidxColumnValue> getColumnValues() {
            return columnValues;
        }

        public static Function<UserPhotosStreamHashAidxRowResult, UserPhotosStreamHashAidxRow> getRowNameFun() {
            return new Function<UserPhotosStreamHashAidxRowResult, UserPhotosStreamHashAidxRow>() {
                @Override
                public UserPhotosStreamHashAidxRow apply(UserPhotosStreamHashAidxRowResult rowResult) {
                    return rowResult.rowName;
                }
            };
        }

        public static Function<UserPhotosStreamHashAidxRowResult, ImmutableSet<UserPhotosStreamHashAidxColumnValue>> getColumnValuesFun() {
            return new Function<UserPhotosStreamHashAidxRowResult, ImmutableSet<UserPhotosStreamHashAidxColumnValue>>() {
                @Override
                public ImmutableSet<UserPhotosStreamHashAidxColumnValue> apply(UserPhotosStreamHashAidxRowResult rowResult) {
                    return rowResult.columnValues;
                }
            };
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(getClass().getSimpleName())
                .add("RowName", getRowName())
                .add("ColumnValues", getColumnValues())
                .toString();
        }
    }

    @Override
    public void delete(UserPhotosStreamHashAidxRow row, UserPhotosStreamHashAidxColumn column) {
        delete(ImmutableMultimap.of(row, column));
    }

    @Override
    public void delete(Iterable<UserPhotosStreamHashAidxRow> rows) {
        Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumn> toRemove = HashMultimap.create();
        Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> result = getRowsMultimap(rows);
        for (Entry<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> e : result.entries()) {
            toRemove.put(e.getKey(), e.getValue().getColumnName());
        }
        delete(toRemove);
    }

    @Override
    public void delete(Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumn> values) {
        t.delete(tableName, ColumnValues.toCells(values));
    }

    @Override
    public void put(UserPhotosStreamHashAidxRow rowName, Iterable<UserPhotosStreamHashAidxColumnValue> values) {
        put(ImmutableMultimap.<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue>builder().putAll(rowName, values).build());
    }

    @Override
    public void put(UserPhotosStreamHashAidxRow rowName, UserPhotosStreamHashAidxColumnValue... values) {
        put(ImmutableMultimap.<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue>builder().putAll(rowName, values).build());
    }

    @Override
    public void put(Multimap<UserPhotosStreamHashAidxRow, ? extends UserPhotosStreamHashAidxColumnValue> values) {
        t.useTable(tableName, this);
        t.put(tableName, ColumnValues.toCellValues(values));
        for (UserPhotosStreamHashAidxTrigger trigger : triggers) {
            trigger.putUserPhotosStreamHashAidx(values);
        }
    }

    @Override
    public void putUnlessExists(UserPhotosStreamHashAidxRow rowName, Iterable<UserPhotosStreamHashAidxColumnValue> values) {
        putUnlessExists(ImmutableMultimap.<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue>builder().putAll(rowName, values).build());
    }

    @Override
    public void putUnlessExists(UserPhotosStreamHashAidxRow rowName, UserPhotosStreamHashAidxColumnValue... values) {
        putUnlessExists(ImmutableMultimap.<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue>builder().putAll(rowName, values).build());
    }

    @Override
    public void putUnlessExists(Multimap<UserPhotosStreamHashAidxRow, ? extends UserPhotosStreamHashAidxColumnValue> rows) {
        Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumn> toGet = Multimaps.transformValues(rows, UserPhotosStreamHashAidxColumnValue.getColumnNameFun());
        Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> existing = get(toGet);
        Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> toPut = HashMultimap.create();
        for (Entry<UserPhotosStreamHashAidxRow, ? extends UserPhotosStreamHashAidxColumnValue> entry : rows.entries()) {
            if (!existing.containsEntry(entry.getKey(), entry.getValue())) {
                toPut.put(entry.getKey(), entry.getValue());
            }
        }
        put(toPut);
    }

    @Override
    public void touch(Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumn> values) {
        Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> currentValues = get(values);
        put(currentValues);
        Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumn> toDelete = HashMultimap.create(values);
        for (Map.Entry<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> e : currentValues.entries()) {
            toDelete.remove(e.getKey(), e.getValue().getColumnName());
        }
        delete(toDelete);
    }

    public static ColumnSelection getColumnSelection(Collection<UserPhotosStreamHashAidxColumn> cols) {
        return ColumnSelection.create(Collections2.transform(cols, Persistables.persistToBytesFunction()));
    }

    public static ColumnSelection getColumnSelection(UserPhotosStreamHashAidxColumn... cols) {
        return getColumnSelection(Arrays.asList(cols));
    }

    @Override
    public Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> get(Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumn> cells) {
        Set<Cell> rawCells = ColumnValues.toCells(cells);
        Map<Cell, byte[]> rawResults = t.get(tableName, rawCells);
        Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> rowMap = HashMultimap.create();
        for (Entry<Cell, byte[]> e : rawResults.entrySet()) {
            if (e.getValue().length > 0) {
                UserPhotosStreamHashAidxRow row = UserPhotosStreamHashAidxRow.BYTES_HYDRATOR.hydrateFromBytes(e.getKey().getRowName());
                UserPhotosStreamHashAidxColumn col = UserPhotosStreamHashAidxColumn.BYTES_HYDRATOR.hydrateFromBytes(e.getKey().getColumnName());
                Long val = UserPhotosStreamHashAidxColumnValue.hydrateValue(e.getValue());
                rowMap.put(row, UserPhotosStreamHashAidxColumnValue.of(col, val));
            }
        }
        return rowMap;
    }

    @Override
    public Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> getAsync(final Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumn> cells, ExecutorService exec) {
        Callable<Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue>> c =
                new Callable<Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue>>() {
            @Override
            public Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> call() {
                return get(cells);
            }
        };
        return AsyncProxy.create(exec.submit(c), Multimap.class);
    }

    @Override
    public List<UserPhotosStreamHashAidxColumnValue> getRowColumns(UserPhotosStreamHashAidxRow row) {
        return getRowColumns(row, ColumnSelection.all());
    }

    @Override
    public List<UserPhotosStreamHashAidxColumnValue> getRowColumns(UserPhotosStreamHashAidxRow row, ColumnSelection columns) {
        byte[] bytes = row.persistToBytes();
        RowResult<byte[]> rowResult = t.getRows(tableName, ImmutableSet.of(bytes), columns).get(bytes);
        if (rowResult == null) {
            return ImmutableList.of();
        } else {
            List<UserPhotosStreamHashAidxColumnValue> ret = Lists.newArrayListWithCapacity(rowResult.getColumns().size());
            for (Entry<byte[], byte[]> e : rowResult.getColumns().entrySet()) {
                UserPhotosStreamHashAidxColumn col = UserPhotosStreamHashAidxColumn.BYTES_HYDRATOR.hydrateFromBytes(e.getKey());
                Long val = UserPhotosStreamHashAidxColumnValue.hydrateValue(e.getValue());
                ret.add(UserPhotosStreamHashAidxColumnValue.of(col, val));
            }
            return ret;
        }
    }

    @Override
    public Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> getRowsMultimap(Iterable<UserPhotosStreamHashAidxRow> rows) {
        return getRowsMultimapInternal(rows, ColumnSelection.all());
    }

    @Override
    public Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> getRowsMultimap(Iterable<UserPhotosStreamHashAidxRow> rows, ColumnSelection columns) {
        return getRowsMultimapInternal(rows, columns);
    }

    @Override
    public Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> getAsyncRowsMultimap(Iterable<UserPhotosStreamHashAidxRow> rows, ExecutorService exec) {
        return getAsyncRowsMultimap(rows, ColumnSelection.all(), exec);
    }

    @Override
    public Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> getAsyncRowsMultimap(final Iterable<UserPhotosStreamHashAidxRow> rows, final ColumnSelection columns, ExecutorService exec) {
        Callable<Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue>> c =
                new Callable<Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue>>() {
            @Override
            public Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> call() {
                return getRowsMultimapInternal(rows, columns);
            }
        };
        return AsyncProxy.create(exec.submit(c), Multimap.class);
    }

    private Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> getRowsMultimapInternal(Iterable<UserPhotosStreamHashAidxRow> rows, ColumnSelection columns) {
        SortedMap<byte[], RowResult<byte[]>> results = t.getRows(tableName, Persistables.persistAll(rows), columns);
        return getRowMapFromRowResults(results.values());
    }

    private static Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> getRowMapFromRowResults(Collection<RowResult<byte[]>> rowResults) {
        Multimap<UserPhotosStreamHashAidxRow, UserPhotosStreamHashAidxColumnValue> rowMap = HashMultimap.create();
        for (RowResult<byte[]> result : rowResults) {
            UserPhotosStreamHashAidxRow row = UserPhotosStreamHashAidxRow.BYTES_HYDRATOR.hydrateFromBytes(result.getRowName());
            for (Entry<byte[], byte[]> e : result.getColumns().entrySet()) {
                UserPhotosStreamHashAidxColumn col = UserPhotosStreamHashAidxColumn.BYTES_HYDRATOR.hydrateFromBytes(e.getKey());
                Long val = UserPhotosStreamHashAidxColumnValue.hydrateValue(e.getValue());
                rowMap.put(row, UserPhotosStreamHashAidxColumnValue.of(col, val));
            }
        }
        return rowMap;
    }

    public BatchingVisitableView<UserPhotosStreamHashAidxRowResult> getAllRowsUnordered() {
        return getAllRowsUnordered(ColumnSelection.all());
    }

    public BatchingVisitableView<UserPhotosStreamHashAidxRowResult> getAllRowsUnordered(ColumnSelection columns) {
        return BatchingVisitables.transform(t.getRange(tableName, RangeRequest.builder().retainColumns(columns).build()),
                new Function<RowResult<byte[]>, UserPhotosStreamHashAidxRowResult>() {
            @Override
            public UserPhotosStreamHashAidxRowResult apply(RowResult<byte[]> input) {
                return UserPhotosStreamHashAidxRowResult.of(input);
            }
        });
    }

    @Override
    public List<String> findConstraintFailures(Map<Cell, byte[]> writes,
                                               ConstraintCheckingTransaction transaction,
                                               AtlasDbConstraintCheckingMode constraintCheckingMode) {
        return ImmutableList.of();
    }

    @Override
    public List<String> findConstraintFailuresNoRead(Map<Cell, byte[]> writes,
                                                     AtlasDbConstraintCheckingMode constraintCheckingMode) {
        return ImmutableList.of();
    }

    /**
     * This exists to avoid unused import warnings
     * {@link AbortingVisitor}
     * {@link AbortingVisitors}
     * {@link ArrayListMultimap}
     * {@link Arrays}
     * {@link AssertUtils}
     * {@link AsyncProxy}
     * {@link AtlasDbConstraintCheckingMode}
     * {@link AtlasDbDynamicMutableExpiringTable}
     * {@link AtlasDbDynamicMutablePersistentTable}
     * {@link AtlasDbMutableExpiringTable}
     * {@link AtlasDbMutablePersistentTable}
     * {@link AtlasDbNamedExpiringSet}
     * {@link AtlasDbNamedMutableTable}
     * {@link AtlasDbNamedPersistentSet}
     * {@link BatchingVisitable}
     * {@link BatchingVisitableView}
     * {@link BatchingVisitables}
     * {@link Bytes}
     * {@link Callable}
     * {@link Cell}
     * {@link Cells}
     * {@link Collection}
     * {@link Collections2}
     * {@link ColumnSelection}
     * {@link ColumnValue}
     * {@link ColumnValues}
     * {@link ComparisonChain}
     * {@link Compression}
     * {@link CompressionUtils}
     * {@link ConstraintCheckingTransaction}
     * {@link Descending}
     * {@link EncodingUtils}
     * {@link Entry}
     * {@link EnumSet}
     * {@link ExecutorService}
     * {@link Function}
     * {@link Generated}
     * {@link HashMultimap}
     * {@link HashSet}
     * {@link Hashing}
     * {@link Hydrator}
     * {@link ImmutableList}
     * {@link ImmutableMap}
     * {@link ImmutableMultimap}
     * {@link ImmutableSet}
     * {@link InvalidProtocolBufferException}
     * {@link IterableView}
     * {@link Iterables}
     * {@link Iterator}
     * {@link Joiner}
     * {@link List}
     * {@link Lists}
     * {@link Map}
     * {@link Maps}
     * {@link MoreObjects}
     * {@link Multimap}
     * {@link Multimaps}
     * {@link NamedColumnValue}
     * {@link Namespace}
     * {@link Objects}
     * {@link Optional}
     * {@link Persistable}
     * {@link Persistables}
     * {@link Prefix}
     * {@link PtBytes}
     * {@link RangeRequest}
     * {@link RowResult}
     * {@link Set}
     * {@link Sets}
     * {@link Sha256Hash}
     * {@link SortedMap}
     * {@link Supplier}
     * {@link Throwables}
     * {@link TimeUnit}
     * {@link Transaction}
     * {@link TypedRowResult}
     * {@link UnsignedBytes}
     * {@link ValueType}
     */
    static String __CLASS_HASH = "jiqbJ5WTE2nHigPqzGohBg==";
}
