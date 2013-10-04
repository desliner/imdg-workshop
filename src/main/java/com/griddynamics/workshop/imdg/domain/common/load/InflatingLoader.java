package com.griddynamics.workshop.imdg.domain.common.load;

import com.google.common.io.Files;

import java.util.zip.GZIPInputStream;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/2/13
 */
public class InflatingLoader<T> implements Loader<T> {

    private final Loader<T> delegate;

    public InflatingLoader(Loader<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void load(Source source, Callback<T> callback) throws Exception {
        if (Files.getFileExtension(source.getFile().getPath()).equals("gz")) {
            source.setInputStream(new GZIPInputStream(source.getInputStream()));
        }
        delegate.load(source, callback);
    }
}
