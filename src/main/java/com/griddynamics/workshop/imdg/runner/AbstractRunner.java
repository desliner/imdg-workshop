package com.griddynamics.workshop.imdg.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/18/13
 */
public abstract class AbstractRunner {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected abstract void main();

    public static void main(String[] args) throws Exception {
        AbstractRunner instance;
        Class clazz = Class.forName(System.getProperty("sun.java.command"));
        instance = (AbstractRunner) clazz.newInstance();
        instance.main();
    }
}
