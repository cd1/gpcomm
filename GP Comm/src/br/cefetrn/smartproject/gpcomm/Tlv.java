package br.cefetrn.smartproject.gpcomm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A data structure that represents a TLV object. It must contain the tag field,
 * which is a number of up to 4 bytes; the length of the value field, also with
 * 4 bytes; and the value field, limited by the length field.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 */
public class Tlv {
    /** The default tag, used when no ta is specified. */
    public static final int DEFAULT_TAG = 0;
    
    /** A zero-length byte array, created once to decrease memory footprint. */
    private static final byte[] ZERO_LENGTH_BYTE_ARRAY = new byte[0];
    /** The tag. */
    private int tag;
    /** The value. */
    private byte[] value;
    
    /**
     * Creates a new TLV object, with the default tag and no value.
     * 
     * @see #DEFAULT_TAG
     */
    public Tlv() {
        setTag(DEFAULT_TAG);
        setValue(ZERO_LENGTH_BYTE_ARRAY);
    }
    
    /**
     * Creates a new TLV object, with the specified tag and value.
     * 
     * @param tag The tag of the object.
     * @param value The value of the object.
     */
    public Tlv(int tag, byte[] value) {
        setTag(tag);
        setValue(value);
    }
    
    /**
     * Obtains the tag of this object.
     * 
     * @return The tag of the TLV object.
     */
    public int getTag() {
        return tag;
    }
    
    /**
     * Changes the tag of this object.
     * 
     * @param tag The new tag.
     */
    public void setTag(int tag) {
        this.tag = tag;
    }
    
    /**
     * Obtains the length of the value of this object.
     * 
     * @return The length of the TLV object.
     */
    public int getLength() {
        return value.length;
    }
    
    /**
     * Obtains the value of this object.
     * 
     * @return The value of the TLV object.
     */
    public byte[] getValue() {
        return value.clone();
    }
    
    /**
     * Changes the value of this object.
     * 
     * @param value The new value. If this parameter is {@code null}, there will
     * be no value.
     */
    public void setValue(byte[] value) {
        if (value == null) {
            value = new byte[0];
        }
        this.value = value.clone();
    }
    
    /**
     * Converts this object into a byte array, in the proper sequence
     * tag-length-value.
     * 
     * @return The array representation of this TLV object.
     */
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
    
    /** {@inheritDoc} */
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