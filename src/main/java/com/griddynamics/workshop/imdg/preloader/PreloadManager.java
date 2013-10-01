package com.griddynamics.workshop.imdg.preloader;

import com.griddynamics.workshop.imdg.domain.stackoverflow.load.PostLoader;
import com.griddynamics.workshop.imdg.domain.stackoverflow.model.Post;
import com.tangosol.net.CacheFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/30/13
 */
public class PreloadManager {

    private static final Logger log = LoggerFactory.getLogger(PreloadManager.class);

    public void preloadAll() {
        try {
            new PreloadWorker<Post>().preload(
                    new File("e:\\Downloads\\Stack Exchange Data Dump - Jun 2013\\Content\\stackoverflow.com\\Posts.xml"),
                    CacheFactory.getCache("posts"),
                    new PostLoader(),
                    Long.MAX_VALUE);
        } catch (Exception e) {
            log.error("Error while preloading", e);
        }
    }
}
