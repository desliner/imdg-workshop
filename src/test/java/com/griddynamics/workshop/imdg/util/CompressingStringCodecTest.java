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
