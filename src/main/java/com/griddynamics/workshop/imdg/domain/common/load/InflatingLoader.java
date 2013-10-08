package com.griddynamics.workshop.imdg.domain.common.load;

import com.google.common.io.Files;

import java.util.zip.GZIPInputStream;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/2/13
 */
public class InflatingLoader<T> extends DelegatingLoader<T> implements Loader<T> {

    public InflatingLoader(Loader<T> delegate) {
        super(delegate);
    }

    @Override
    public void load(Source source, Callback<T> callback) throws Exception {
        if (Files.getFileExtension(source.getFile().getPath()).equals("gz")) {
            log.info("Enabling GZIP decompression");
            source.setInputStream(new GZIPInputStream(source.getInputStream()));
        }
        getDelegate().load(source, callback);
    }
}
