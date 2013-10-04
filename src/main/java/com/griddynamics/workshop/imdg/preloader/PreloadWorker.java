package com.griddynamics.workshop.imdg.preloader;

import com.griddynamics.workshop.imdg.domain.common.load.BatchLoader;
import com.griddynamics.workshop.imdg.domain.common.load.Loader;
import com.griddynamics.workshop.imdg.domain.common.load.TrackingLoader;
import com.griddynamics.workshop.imdg.domain.common.model.Entity;
import com.tangosol.net.NamedCache;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/1/13
 */
public class PreloadWorker<T extends Entity> {

    private static final int BATCH_SIZE = 1000;

    private static final int THREAD_COUNT = 4;
    private static final int QUEUE_SIZE = 20;

    private ExecutorService executor;

    public PreloadWorker() {
        executor = new ThreadPoolExecutor(THREAD_COUNT, THREAD_COUNT, Integer.MAX_VALUE, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>(QUEUE_SIZE) {
            @Override
            public boolean offer(Runnable runnable) {
                try {
                    super.put(runnable);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        });
    }

    private void putToCache(NamedCache destination, Collection<T> batch) {
        PutTask task = new PutTask<T>(destination, new ArrayList<T>(batch));
        executor.submit(task);
    }

    public void preload(File file, final NamedCache destination, Loader<T> loader, double needed) throws Exception {
        Loader<Collection<T>> finalLoader = new BatchLoader<T>(BATCH_SIZE, new TrackingLoader<T>(needed, loader));
        Loader.Source source = new Loader.Source();
        source.setFile(file);
        source.setInputStream(new BufferedInputStream(new FileInputStream(file)));
        try {
            finalLoader.load(source, new Loader.Callback<Collection<T>>() {
                @Override
                public boolean onLoad(Collection<T> batch) {
                    putToCache(destination, batch);
                    return true;
                }
            });
        } finally {
            IOUtils.closeQuietly(source.getInputStream());
        }
        executor.shutdown();
    }

    private static class PutTask<T extends Entity> implements Runnable {
        private NamedCache destination;
        private Collection<T> batch;

        private PutTask(NamedCache destination, Collection<T> batch) {
            this.destination = destination;
            this.batch = batch;
        }

        @Override
        public void run() {
            Map<Object, T> map = new HashMap<Object, T>(batch.size());
            for (T entity : batch) {
                map.put(entity.getId(), entity);
            }
            destination.putAll(map);
        }
    }
}
