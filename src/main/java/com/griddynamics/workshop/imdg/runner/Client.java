package com.griddynamics.workshop.imdg.runner;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/6/13
 */
public class Client extends Storage {

    protected final Logger log = LoggerFactory.getLogger(getClass());

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

    public static void main(String[] args) {
        Client instance = new Client();
        try {
            Class clazz = Class.forName(System.getProperty("sun.java.command"));
            instance = (Client) clazz.newInstance();
        } catch (Exception ignored) {
        }
        instance.main();
    }
}
