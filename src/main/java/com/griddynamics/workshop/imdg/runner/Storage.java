package com.griddynamics.workshop.imdg.runner;

import com.tangosol.net.DefaultCacheServer;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/6/13
 */
public class Storage {

    public void main() {
        configure();
        run();
        System.exit(0);
    }

    protected void configure() {
        System.setProperty("tangosol.coherence.override", "coherence-override.xml");
        if (System.getProperty("imdg.remote") != null) {
            System.setProperty("tangosol.coherence.cacheconfig", "cache-config-remote.xml");
        } else {
            System.setProperty("tangosol.coherence.cacheconfig", "cache-config.xml");
        }
    }

    protected void run() {
        DefaultCacheServer.start();
        while (true) {
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Storage().main();
    }
}
