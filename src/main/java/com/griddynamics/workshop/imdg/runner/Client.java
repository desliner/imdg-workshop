package com.griddynamics.workshop.imdg.runner;

import com.tangosol.net.CacheFactory;

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
        System.out.println(CacheFactory.getCache("a").get(0));
    }

    public static void main(String[] args) {
        new Client().main();
    }
}
