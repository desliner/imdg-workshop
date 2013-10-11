package com.griddynamics.workshop.imdg.runner.scenario;

import com.griddynamics.workshop.imdg.domain.stackoverflow.model.User;
import com.griddynamics.workshop.imdg.runner.Client;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/11/13
 */
public class S4_Near extends Client {

    @Override
    protected void run() {
        NamedCache usersCache = CacheFactory.getCache("users");

        long time = System.currentTimeMillis();
        User user = (User) usersCache.get(11);
        time = System.currentTimeMillis() - time;
        log.info("Fresh get completed in {} ms", time);

        time = System.currentTimeMillis();
        user = (User) usersCache.get(11);
        time = System.currentTimeMillis() - time;
        log.info("Get from Near completed in {} ms", time);

        time = System.currentTimeMillis();
        user = (User) usersCache.get(12);
        time = System.currentTimeMillis() - time;
        log.info("Another fresh get completed in {} ms", time);
    }
}
