package com.palantir.atlasdb.keyvalue.partition;

import com.palantir.atlasdb.keyvalue.api.KeyValueService;

public abstract class KeyValueServiceWithStatus {
    final KeyValueService service;
    final KeyValueService get() {
        return service;
    }
    public KeyValueServiceWithStatus(KeyValueService service) {
        this.service = service;
    }
    public abstract boolean shouldUseForRead();
    public abstract boolean shouldCountForRead();
    public abstract boolean shouldUseForWrite();
    public abstract boolean shouldCountForWrite();

    public boolean shouldUseFor(boolean write) {
        if (write) {
            return shouldUseForWrite();
        }
        return shouldUseForRead();
    }

    public boolean shouldCountFor(boolean write) {
        if (write) {
            return shouldCountForWrite();
        }
        return shouldCountForRead();
    }
}