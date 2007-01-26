package br.cefetrn.smartproject.gpcomm;

import java.io.ByteArrayOutputStream;

/**
 * A class with helper methods to convert byte and short values to String, and
 * String to byte and short values. Every String returned or used by the methods
 * of this class are represented in hexadecimal. A byte value must be formatted
 * with exactly two characters and a short value must be formatted with exactly
 * four characters. All the six letters used by the hexadecimal notation will
 * appear in upper case.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 */
public class Util {
    /** The radix of the hexadecimal notation. */
    public static final int HEXADECIMAL_RADIX = 16;
    
    private Util() {
        // no instantiation
    }
    
    /**
     * Converts an array of bytes to a String. Each byte will be separated by
     * one colon.
     * 
     * @param array An array of bytes.
     * @return The String convertion of {@code array}.
     * @see #toString(byte)
     */
    public static String toString(byte[] array) {
        StringBuilder sb = new StringBuilder();
        appendByteArrayAsString(array, sb);
        return sb.toString();
    }
    
    /**
     * Converts a String to an array of bytes. The String must be formed with
     * bytes represented in hexadecimal, and each value must be separated by a
     * colon.
     * 
     * @param s A String containing several byte values in hexadecimal.
     * @return The array representation of {@code s}, or {@code null} if
     * {@code s == null}.
     */
    public static byte[] toByteArray(String s) {
        if (s == null) {
            return null;
        }
        String[] bytes = s.split(":");
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
        for (String b : bytes) {
            baos.write(toByte(b));
        }
        return baos.toByteArray();
    }
    
    /**
     * Appends an array of bytes to an existing StringBuilder. If {@code array}
     * is {@code null}, then it will be appended only the String "null".
     * 
     * @param array An array of bytes.
     * @param sb An existing StringBuilder.
     * @see #toString(byte[])
     */
    public static void appendByteArrayAsString(byte[] array, StringBuilder sb) {
        if (array == null) {
            sb.append("null");
        }
        else if (array.length == 0) {
            sb.append("<empty>");
        }
        else {
            for (byte b : array) {
                appendByteAsString(b, sb);
                sb.append(":");
            }
            sb.deleteCharAt(sb.length() - 1); // deletes the last space
        }
    }
    
    /**
     * Converts a single byte to a String.
     * 
     * @param b A byte.
     * @return A String containing the byte {@code b} formatted in hexadecimal.
     */
    public static String toString(byte b) {
        StringBuilder sb = new StringBuilder(2);
        appendByteAsString(b, sb);
        return sb.toString();
    }
    
    public static byte toByte(String s) {
        return (byte) Integer.parseInt(s, HEXADECIMAL_RADIX);
    }
    
    /**
     * Appends a formatted byte into an existing StringBuilder.
     * 
     * @param b A byte value.
     * @param sb An existing StringBuilder.
     * @see #toString(byte)
     */
    public static void appendByteAsString(byte b, StringBuilder sb) {
        String tmp = Integer.toHexString(b & 0xFF).toUpperCase();
        if (tmp.length() == 1) {
            sb.append("0");
        }
        sb.append(tmp);
    }
    
    /**
     * Converts a short value into a String.
     * 
     * @param s A short value.
     * @return A String containing the short {@code s} formatted in hexadecimal.
     */
    public static String toString(short s) {
        StringBuilder sb = new StringBuilder(4);
        appendShortAsString(s, sb);
        return sb.toString();
    }
    
    public static short toShort(String s) {
        return (short) Integer.parseInt(s, HEXADECIMAL_RADIX);
    }
    
    /**
     * Appends a formatted short value into an existing StringBuilder.
     * 
     * @param s A short value.
     * @param sb An existing StringBuilder.
     * @see #toString(byte)
     */
    public static void appendShortAsString(short s, StringBuilder sb) {
        String tmp = Integer.toHexString(s & 0xFFFF).toUpperCase();
        int n_zeros = 4 - tmp.length();
        for (int i = 0; i < n_zeros; i++) {
            sb.append("0");
        }
        sb.append(tmp);
    }
    
    /**
     * Generates a short value using two bytes.
     * 
     * @param b1 The most significant byte.
     * @param b2 The least significant byte.
     * @return A short value, in the form [b1 b2].
     */
    public static short makeShort(byte b1, byte b2) {
        return (short) ((b1 << Byte.SIZE) + b2);
    }
    
    /**
     * Generates a short value using two bytes inside an array.
     * 
     * @param array An array of bytes, with {@code array.length >= 2}.
     * @param offset The offset of the most significant byte of the short value.
     * The least significant byte will be the following byte.
     * @return A short value.
     */
    public static short getShort(byte[] array, int offset) {
        return makeShort(array[offset], array[offset + 1]);
    }
    
    /**
     * Puts a short value inside an array of bytes.
     * 
     * @param array An array of bytes, with {@code array.length >= 2}.
     * @param offset The offset in where the most significant byte of
     * {@code value} will be put. The least significant byte of {@code value}
     * will be put in the following location.
     * @param value The short value that will be put inside {@code array}.
     */
    public static void setShort(byte[] array, int offset, short value) {
        array[offset] = (byte) (value >> Byte.SIZE);
        array[offset + 1] = (byte) (value & 0xFF);
    }
}