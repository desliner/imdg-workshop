package com.griddynamics.workshop.imdg.runner;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/6/13
 */
public class Client extends Storage {

    @Override
    protected void configure() {
        super.configure();
        System.setProperty("tangosol.coherence.distributed.localstorage", "false");
    }

    @Override
    protected void run() {
        NamedCache postsCache = CacheFactory.getCache("posts");
        NamedCache usersCache = CacheFactory.getCache("users");

        log.info("Users: {}", usersCache.size());
        log.info("Posts: {}", postsCache.size());
    }
}
