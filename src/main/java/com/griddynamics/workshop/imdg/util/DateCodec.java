package com.griddynamics.workshop.imdg.util;

import com.griddynamics.workshop.imdg.util.gridkit.ReflectionPofSerializer;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.reflect.PofValue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/3/13
 */
public class DateCodec implements ReflectionPofSerializer.ObjectPropCodec {

    @Override
    public Object readProp(PofReader reader, int propId, Field field) throws IOException {
        long time = reader.readLong(propId);
        return new Date(time);
    }

    @Override
    public void writeProp(PofWriter writer, int propId, Object obj, Field field) throws IOException {
        Date date = (Date) obj;
        writer.writeLong(propId, date.getTime());
    }

    @Override
    public String extract(PofValue cursor, String path, Object[] valueOut) throws IOException {
        throw new UnsupportedOperationException();
    }
}
