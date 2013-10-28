/*
 * Copyright (c) 2013 Grid Dynamics International, Inc. All Rights Reserved
 * http://www.griddynamics.com
 *
 * IMDG Workshop is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $Id: $
 * @Project:     IMDG Workshop
 * @Description: Demo application for IMDG based on Oracle Coherence
 */

package com.griddynamics.workshop.imdg.domain.stackoverflow.load;

import com.griddynamics.workshop.imdg.domain.stackoverflow.load.setter.ValueSetter;
import com.griddynamics.workshop.imdg.domain.common.load.Loader;
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
