package com.upstream.basemvvmimpl.data.cache;

import android.content.Context;
import com.upstream.basemvvmimpl.data.cache.serializer.Serializer;
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor;
import com.upstream.basemvvmimpl.presentation.utils.PreferenceManager;

public abstract class BaseCachePreference<T> extends BaseCache<T> {

    private final PreferenceManager preferenceManager;

    BaseCachePreference(Context context, Serializer serializer, CacheFileManager fileManager, ThreadExecutor executor, PreferenceManager preferenceManager) {
        super(context, serializer, fileManager, executor);
        this.preferenceManager = preferenceManager;
    }

    protected abstract String getPreferenceLastCacheUpdateKey();

    protected abstract long getExpirationTimeMillis();

    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.preferenceManager.savePreference(getPreferenceLastCacheUpdateKey(), currentMillis);
    }

    private long getLastCacheUpdateTimeMillis() {
        return this.preferenceManager.getLongPreference(getPreferenceLastCacheUpdateKey(), 0);
    }

    @Override
    public boolean put(T entity) {
        if (super.put(entity)) {
            setLastCacheUpdateTimeMillis();
            return true;
        }
        return false;
    }

    public boolean isExpired() {

        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > getExpirationTimeMillis());
        if (expired) {
            evict();
        }

        return expired;
    }
}
