package com.griddynamics.workshop.imdg.domain.common.load;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/1/13
 */
public class BatchLoader<T> extends DelegatingLoader<T> implements Loader<Collection<T>> {

    private final int batchSize;

    public BatchLoader(int batchSize, Loader<T> delegate) {
        super(delegate);
        this.batchSize = batchSize;
    }

    @Override
    public void load(Source source, final Callback<Collection<T>> callback) throws Exception {
        final List<T> batch = new ArrayList<T>(batchSize);
        getDelegate().load(source, new Callback<T>() {
            @Override
            public boolean onLoad(T entity) throws Exception {
                batch.add(entity);
                if (batch.size() < batchSize) {
                    return true;
                } else {
                    boolean result = callback.onLoad(batch);
                    batch.clear();
                    return result;
                }
            }
        });
        if (batch.size() > 0) {
            callback.onLoad(batch);
        }
    }
}
