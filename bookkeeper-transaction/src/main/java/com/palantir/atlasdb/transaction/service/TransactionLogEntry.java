/**
 * Copyright 2015 Palantir Technologies
 *
 * Licensed under the BSD-3 License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.palantir.atlasdb.transaction.service;

public class TransactionLogEntry {
    private final long startTimestamp;
    private final long commitTimestamp;

    public TransactionLogEntry(long startTimestamp, long commitTimestamp) {
        this.startTimestamp = startTimestamp;
        this.commitTimestamp = commitTimestamp;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public long getCommitTimestamp() {
        return commitTimestamp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (commitTimestamp ^ (commitTimestamp >>> 32));
        result = prime * result + (int) (startTimestamp ^ (startTimestamp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TransactionLogEntry other = (TransactionLogEntry) obj;
        if (commitTimestamp != other.commitTimestamp)
            return false;
        if (startTimestamp != other.startTimestamp)
            return false;
        return true;
    }
}
