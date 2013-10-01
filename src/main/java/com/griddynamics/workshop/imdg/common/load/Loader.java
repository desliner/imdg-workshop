package com.griddynamics.workshop.imdg.common.load;

import java.io.File;
import java.io.InputStream;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/30/13
 */
public interface Loader<T> {

    void load(Source source, Callback<T> callback) throws Exception;

    public interface Callback<T> {
        boolean onLoad(T entity) throws Exception;
    }

    public class Source {
        private File file;
        private InputStream inputStream;

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        public void setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }
    }
}
