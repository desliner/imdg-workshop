package com.griddynamics.workshop.imdg.util;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.reflect.PofValue;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;
import org.gridkit.coherence.utils.pof.ReflectionPofSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
* @author mmyslyvtsev@griddynamics.com
* @since 10/2/13
*/
class CompressingStringCodec implements ReflectionPofSerializer.ObjectPropCodec {

    private final LZ4Factory lz4Factory = LZ4Factory.nativeInstance();
    private final LZ4Compressor lz4Compressor = lz4Factory.fastCompressor();
    private final LZ4FastDecompressor lz4Decompressor = lz4Factory.fastDecompressor();

    public static final int COMPRESS_THRESHOLD = 100;
    private static final int NOT_COMPRESSED_MAGIC = 0; // Must be less than COMPRESS_THRESHOLD

    @Override
    public Object readProp(PofReader reader, int propId, Field field) throws IOException {
        byte[] bytes = reader.readByteArray(propId);
        int length = getInt(bytes);
        String result;
        if (length == NOT_COMPRESSED_MAGIC) {
            result = new String(bytes, 4, bytes.length - 4, Charset.defaultCharset());
        } else {
            byte[] decompressedBytes = decompress(bytes, 4, length);
            result = new String(decompressedBytes, Charset.defaultCharset());
        }
        return result;
    }

    @Override
    public void writeProp(PofWriter writer, int propId, Object obj, Field field) throws IOException {
        String string = (String) obj;
        byte[] stringBytes = string.getBytes(Charset.defaultCharset());
        byte[] result;
        if (stringBytes.length < COMPRESS_THRESHOLD) {
            result = new byte[4 + stringBytes.length];
            setInt(result, NOT_COMPRESSED_MAGIC);
            System.arraycopy(stringBytes, 0, result, 4, stringBytes.length);
        } else {
            byte[] compressedBytes = compress(stringBytes);
            result = new byte[4 + compressedBytes.length];
            setInt(result, stringBytes.length);
            System.arraycopy(compressedBytes, 0, result, 4, compressedBytes.length);
        }
        writer.writeByteArray(propId, result);
    }

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

    @Override
    public String extract(PofValue cursor, String path, Object[] valueOut) throws IOException {
        throw new UnsupportedOperationException(); //TODO
    }
}
