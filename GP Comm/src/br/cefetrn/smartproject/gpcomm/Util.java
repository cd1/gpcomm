package br.cefetrn.smartproject.gpcomm;

/**
 * A class with auxiliary methods to convert byte and short values to String, in
 * hexadecimal. A byte value will be formatted with exactly two characters and a
 * short value will be converted in exactly four characters. The String "0x"
 * will be prepended in every value and all the six letters will appear in upper
 * case.
 * 
 * @author Cr�stian Deives <cristiandeives@gmail.com>
 */
public class Util {
    private Util() {
        // no instantiation
    }
    
    /**
     * Converts an array of bytes to a String. Each byte will be separated by
     * one space.
     * 
     * @param array An array of bytes.
     * @return The String convertion of {@code array}.
     * @see #fromByteToString(byte)
     */
    public static String fromByteArrayToString(byte[] array) {
        StringBuilder sb = new StringBuilder();
        appendByteArrayAsString(array, sb);
        return sb.toString();
    }
    
    /**
     * Appends an array of bytes to an existing StringBuilder. If {@code array}
     * is {@code null}, then it will be appended only the String "null".
     * 
     * @param array An array of bytes.
     * @param sb An existing StringBuilder.
     * @see #fromByteArrayToString(byte[])
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
                sb.append(" ");
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
    public static String fromByteToString(byte b) {
        StringBuilder sb = new StringBuilder(4); // 0xXX
        appendByteAsString(b, sb);
        return sb.toString();
    }
    
    /**
     * Appends a formatted byte into an existing StringBuilder.
     * 
     * @param b A byte value.
     * @param sb An existing StringBuilder.
     * @see #fromByteToString(byte)
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
    public static String fromShortToString(short s) {
        StringBuilder sb = new StringBuilder(6); // 0xXXXX
        appendShortAsString(s, sb);
        return sb.toString();
    }
    
    /**
     * Appends a formatted short value into an existing StringBuilder.
     * 
     * @param s A short value.
     * @param sb An existing StringBuilder.
     * @see #fromShortToString(byte)
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
        return (short) ((b1 << 8) + b2);
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
        array[offset] = (byte) (value >> 8);
        array[offset + 1] = (byte) (value & 0xFF);
    }
}