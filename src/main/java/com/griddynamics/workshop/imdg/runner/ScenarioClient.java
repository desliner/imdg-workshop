package com.griddynamics.workshop.imdg.runner;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/3/13
 */
public abstract class ScenarioClient extends Client {

    @Override
    protected abstract void run();

    public static void main(String[] args) {
        ScenarioClient instance;
        try {
            String className = args[0];
            Class c = Class.forName("com.griddynamics.workshop.imdg.runner.scenario." + className);
            instance = (ScenarioClient) c.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        instance.main();
    }
}
