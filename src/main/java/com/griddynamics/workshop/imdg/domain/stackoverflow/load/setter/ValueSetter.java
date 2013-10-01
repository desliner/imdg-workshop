package com.griddynamics.workshop.imdg.domain.stackoverflow.load.setter;

/**
* @author mmyslyvtsev@griddynamics.com
* @since 10/1/13
*/
public interface ValueSetter<T> {
    void set(T target, String value);
}
