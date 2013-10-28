package com.griddynamics.workshop.imdg.runner.research.repartitioning;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/18/13
 */
public class Storage extends com.griddynamics.workshop.imdg.runner.Storage {

    @Override
    protected void configure() {
        super.configure();
        System.setProperty("tangosol.coherence.cacheconfig", "research/cache-config.xml");
    }
}
