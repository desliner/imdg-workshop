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

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
* @author mmyslyvtsev@griddynamics.com
* @since 10/2/13
*/
public class CompressingStringCodec {

    private final LZ4Factory lz4Factory = LZ4Factory.nativeInstance();
    private final LZ4Compressor lz4Compressor = lz4Factory.fastCompressor();
    private final LZ4FastDecompressor lz4Decompressor = lz4Factory.fastDecompressor();

    private byte[] compress(byte[] data) throws IOException {
        return compressGZIP(data);
    }

    private byte[] compressLZ4(byte[] data) {
        return lz4Compressor.compress(data);
    }

    private byte[] compressGZIP(byte[] data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(data, 0, data.length);
        gzipOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] decompress(byte[] data, int srcOff, int dstLen) throws IOException {
        return decompressGZIP(data, srcOff, dstLen);
    }
    private byte[] decompressLZ4(byte[] data, int srcOff, int dstLen) throws IOException {
        return lz4Decompressor.decompress(data, srcOff, dstLen);
    }

    private byte[] decompressGZIP(byte[] data, int srcOff, int dstLen) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data, srcOff, data.length - srcOff);
        GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] result = new byte[dstLen];
        int pos = 0;
        while (dstLen > 0) {
            int read = gzipInputStream.read(result, pos, dstLen);
            dstLen -= read;
            pos += read;
        }
        gzipInputStream.close();
        return result;
    }

    private void setInt(byte[] bytes, int value) {
        bytes[0] = (byte)(value >>> 24);
        bytes[1] = (byte)(value >>> 16);
        bytes[2] = (byte)(value >>> 8);
        bytes[3] = (byte)value;
    }

    private int getInt(byte[] bytes) {
        return bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
    }


    // PUBLIC STATIC INTERFACE
    // TODO: REFACTOR
    private static final CompressingStringCodec INSTANCE = new CompressingStringCodec();

    public static byte[] compress(String string) throws IOException {
        return INSTANCE.compressInternal(string);
    }

    public static String decompress(byte[] bytes) throws IOException {
        return INSTANCE.decompressInternal(bytes);
    }

    private byte[] compressInternal(String string) throws IOException {
        byte[] result = null;
        if (string != null) {
            byte[] stringBytes = string.getBytes(Charset.defaultCharset());
            byte[] compressedBytes = compress(stringBytes);
            result = new byte[4 + compressedBytes.length];
            setInt(result, stringBytes.length);
            System.arraycopy(compressedBytes, 0, result, 4, compressedBytes.length);
        }
        return result;
    }

    private String decompressInternal(byte[] bytes) throws IOException {
        String result = null;
        if (bytes != null) {
            int length = getInt(bytes);
            byte[] decompressedBytes = decompress(bytes, 4, length);
            result = new String(decompressedBytes, Charset.defaultCharset());
        }
        return result;
    }
}
