package com.griddynamics.workshop.imdg.util;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author mmyslyvtsev@griddynamics.com
 * @since 10/2/13
 */
@RunWith(Parameterized.class)
public class CompressingStringCodecTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {10}, {1000}
        });
    }

    private static final int PROP_ID = 42;

    private CompressingStringCodec sut;
    private PofReader pofReader;
    private PofWriter pofWriter;
    private byte[] data;

    private int size;

    public CompressingStringCodecTest(int size) {
        this.size = size;
    }

    @Before
    public void setUp() throws Exception {
        sut = new CompressingStringCodec();

        pofReader = Mockito.mock(PofReader.class);
        BDDMockito.given(pofReader.readByteArray(PROP_ID)).willAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return data;
            }
        });

        pofWriter = Mockito.mock(PofWriter.class);
        BDDMockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                data = (byte[]) invocationOnMock.getArguments()[1];
                return null;
            }
        }).when(pofWriter).writeByteArray(Mockito.eq(PROP_ID), Mockito.any(byte[].class));
    }

    @Test
    public void test() throws Exception {
        String string = RandomStringUtils.randomAlphabetic(size);
        sut.writeProp(pofWriter, PROP_ID, string, null);
        String deserializedString = (String) sut.readProp(pofReader, PROP_ID, null);
        Assert.assertEquals(string.length(), deserializedString.length());
        Assert.assertEquals(string, deserializedString);
    }
}
