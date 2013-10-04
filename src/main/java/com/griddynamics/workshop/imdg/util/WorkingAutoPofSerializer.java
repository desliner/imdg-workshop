package com.griddynamics.workshop.imdg.util;

import com.tangosol.io.pof.ConfigurablePofContext;
import com.tangosol.net.NamedCache;
import org.gridkit.coherence.utils.pof.AutoPofSerializer;
import org.gridkit.coherence.utils.pof.ReflectionPofSerializer;
import org.joda.time.LocalDateTime;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/2/13
 */
public class WorkingAutoPofSerializer extends AutoPofSerializer {

    public WorkingAutoPofSerializer() {
        super();
        doOverrides();
    }

    public WorkingAutoPofSerializer(String pofConfig) {
        super(pofConfig);
        doOverrides();
    }

    public WorkingAutoPofSerializer(String pofConfig, NamedCache typeMap) {
        super(pofConfig, typeMap);
        doOverrides();
    }

    public WorkingAutoPofSerializer(ConfigurablePofContext serializer, NamedCache typeMap) {
        super(serializer, typeMap);
        doOverrides();
    }

    private void doOverrides() {
        ReflectionPofSerializer.registerCodec(String.class, new CompressingStringCodec()); // TODO: write to Ragozin
        ReflectionPofSerializer.registerCodec(LocalDateTime.class, new LocalDateTimeCodec());
    }
}
