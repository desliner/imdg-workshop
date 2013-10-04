package com.griddynamics.workshop.imdg.runner;

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
    }

    public static void main(String[] args) {
        new Client().main();
    }
}
