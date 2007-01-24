package br.cefetrn.smartproject.gpcomm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class Tlv {
    public static final byte DEFAULT_TAG = (byte) 0x00;
    
    private static final byte[] ZERO_LENGTH_BYTE_ARRAY = new byte[0];
    private byte tag;
    private byte[] value;
    
    public Tlv() {
        setTag(DEFAULT_TAG);
        setValue(ZERO_LENGTH_BYTE_ARRAY);
    }
    
    public Tlv(byte tag, byte[] value) {
        setTag(tag);
        setValue(value);
    }
    
    public Tlv(byte[] data) {
        if (data == null || data.length < 2) {
            throw new IllegalArgumentException("data must be at least 2 " +
                    "bytes long");
        }
        setTag(data[0]);
        byte length = data[1];
        if (length > data.length + 2) {
            throw new IllegalArgumentException("invalid length: " + length);
        }
        System.arraycopy(data, 2, value, 0, length);
    }
    
    public byte getTag() {
        return tag;
    }
    
    public void setTag(byte tag) {
        this.tag = tag;
    }
    
    public byte getLength() {
        return (byte) value.length;
    }
    
    public byte[] getValue() {
        return value.clone();
    }
    
    public void setValue(byte[] value) {
        if (value == null) {
            value = new byte[0];
        }
        this.value = value.clone();
    }
    
    public byte[] toByteArray() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(tag);
        baos.write(getLength());
        try {
            baos.write(value);
        }
        catch (IOException e) {/* it'll never catch */}
        return baos.toByteArray();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("tag=");
        sb.append(tag);
        sb.append(",length=");
        sb.append(getLength());
        sb.append(",value=");
        Util.appendByteArrayAsString(value, sb);
        return sb.toString();
    }
}