package com.griddynamics.workshop.imdg.util;

import junit.framework.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/11/13
 */
public class CompressingStringCodecTestStatic {

    @Test
    public void test() throws Exception {
        String string = RandomStringUtils.randomAlphabetic(10);
        byte[] bytes = CompressingStringCodec.compress(string);
        String string2 = CompressingStringCodec.decompress(bytes);
        Assert.assertEquals(string, string2);
    }

    @Test
    public void testNull() throws Exception {
        String string = null;
        byte[] bytes = CompressingStringCodec.compress(string);
        String string2 = CompressingStringCodec.decompress(bytes);
        Assert.assertEquals(string, string2);
    }
}
