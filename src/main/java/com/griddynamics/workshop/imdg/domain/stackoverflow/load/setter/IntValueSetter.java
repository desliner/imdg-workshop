package com.griddynamics.workshop.imdg.domain.stackoverflow.load.setter;

/**
* @author mmyslyvtsev@griddynamics.com
* @since 10/1/13
*/
public abstract class IntValueSetter<T> implements ValueSetter<T> {

    @Override
    public void set(T target, String value) {
        set(target, Integer.parseInt(value));
    }

    public abstract void set(T target, int value);
}
