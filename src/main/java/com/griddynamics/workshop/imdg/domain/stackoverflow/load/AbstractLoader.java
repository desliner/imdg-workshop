package com.griddynamics.workshop.imdg.domain.stackoverflow.load;

import com.griddynamics.workshop.imdg.domain.stackoverflow.load.setter.ValueSetter;
import com.griddynamics.workshop.imdg.common.load.Loader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/1/13
 */
public abstract class AbstractLoader<T> implements Loader<T> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Map<String, ValueSetter<T>> setters;

    public AbstractLoader() {
        setters = new HashMap<String, ValueSetter<T>>();
        initSetters(setters);
    }

    protected abstract T newInstance();

    protected abstract void initSetters(Map<String, ValueSetter<T>> setters);

    @Override
    public void load(Source source, Callback<T> callback) throws Exception {
        XMLStreamReader reader = XMLInputFactory.newFactory().createXMLStreamReader(source.getInputStream());
        try {
            while (reader.hasNext()) {
                int eventType = reader.next();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (reader.getLocalName().equals("row")) {
                            T entity = newInstance();
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                String attrName = reader.getAttributeLocalName(i);
                                ValueSetter<T> setter = setters.get(attrName);
                                if (setter != null) {
                                    setter.set(entity, reader.getAttributeValue(i));
                                } else {
                                    log.warn("Unknown attribute {} at:\n{}", attrName, reader.getLocation());
                                }
                            }
                            if (!callback.onLoad(entity)) {
                                return;
                            }
                        }
                        break;
                }
            }
        } finally {
            reader.close();
        }
    }
}
