package com.griddynamics.workshop.imdg.runner;

import com.tangosol.net.DefaultCacheServer;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/6/13
 */
public class Storage {

    private static final int PORT = 11000;

    public void main() {
        configure();
        run();
    }

    protected void configure() {
        System.setProperty("tangosol.coherence.ttl", "0");
        System.setProperty("tangosol.coherence.wka", "127.0.0.1");
        System.setProperty("tangosol.coherence.wka.port", String.valueOf(PORT));
        System.setProperty("tangosol.coherence.localhost", "127.0.0.1");
        System.setProperty("tangosol.coherence.localport", String.valueOf(PORT));
        System.setProperty("tangosol.coherence.socketprovider", "tcp");
        System.setProperty("tangosol.coherence.cluster", "workshop-cluster");
        System.setProperty("tangosol.coherence.cacheconfig", "cache-config.xml");
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
