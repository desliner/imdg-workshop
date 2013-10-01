package com.griddynamics.workshop.imdg.domain.stackoverflow.load;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 9/30/13
 */
public class TagsDeserializer {

    public static List<String> deserialize(String value) {
        String[] tags = value.replaceAll("(^<|>$)", "").split("><");
        return Lists.newArrayList(tags);
    }
}
