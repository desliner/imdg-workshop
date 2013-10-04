package com.griddynamics.workshop.imdg.util;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.reflect.PofValue;
import org.gridkit.coherence.utils.pof.ReflectionPofSerializer;
import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/3/13
 */
public class LocalDateTimeCodec implements ReflectionPofSerializer.ObjectPropCodec {

    @Override
    public Object readProp(PofReader reader, int propId, Field field) throws IOException {
        long dateTimeLong = reader.readLong(propId);
        return new LocalDateTime(dateTimeLong);
    }

    @Override
    public void writeProp(PofWriter writer, int propId, Object obj, Field field) throws IOException {
        LocalDateTime localDateTime = (LocalDateTime) obj;
        writer.writeLong(propId, localDateTime.toDate().getTime());
    }

    @Override
    public String extract(PofValue cursor, String path, Object[] valueOut) throws IOException {
        throw new UnsupportedOperationException();
    }
}
