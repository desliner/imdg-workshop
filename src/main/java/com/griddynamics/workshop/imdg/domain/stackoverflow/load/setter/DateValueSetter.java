package com.griddynamics.workshop.imdg.domain.stackoverflow.load.setter;

import org.joda.time.format.ISODateTimeFormat;

import java.util.Date;

/**
* @author mmyslyvtsev@griddynamics.com
* @since 10/1/13
*/
public abstract class DateValueSetter<T> implements ValueSetter<T> {

    @Override
    public void set(T target, String value) {
        Date date = ISODateTimeFormat.dateTimeParser().parseLocalDateTime(value).toDate();
        set(target, date);
    }

    public abstract void set(T target, Date value);
}
