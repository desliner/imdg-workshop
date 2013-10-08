package com.griddynamics.workshop.imdg.domain.common.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/7/13
 */
public abstract class DelegatingLoader<T> {

    private Loader<T> delegate;

    protected Logger log;

    protected DelegatingLoader(Loader<T> delegate) {
        this.delegate = delegate;
        this.log = LoggerFactory.getLogger(getRootDelegate().getClass());
    }

    public Loader<T> getDelegate() {
        return delegate;
    }

    public Loader getRootDelegate() {
        Loader result = delegate;
        while (result instanceof DelegatingLoader) {
            result = ((DelegatingLoader) result).getDelegate();
        }
        return result;
    }
}
