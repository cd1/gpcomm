package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class Util {
    public static String fromByteArrayToString(byte[] array) {
        StringBuilder sb = new StringBuilder();
        appendByteArrayAsString(array, sb);
        return sb.toString();
    }
    
    public static void appendByteArrayAsString(byte[] array, StringBuilder sb) {
        if (array == null) {
            sb.append("null");
        }
        else {
            for (byte b : array) {
                appendByteAsString(b, sb);
                sb.append(" ");
            }
            if (sb.length() > 0) { // all the cases, except when array = byte[0]
                sb.deleteCharAt(sb.length() - 1); // deletes the last space
            }
        }
    }
    
    public static String fromByteToString(byte b) {
        StringBuilder sb = new StringBuilder(4); // 0xXX
        appendByteAsString(b, sb);
        return sb.toString();
    }
    
    public static void appendByteAsString(byte b, StringBuilder sb) {
        String tmp = Integer.toHexString(b & 0xFF).toUpperCase();
        sb.append("0x");
        if (tmp.length() == 1) {
            sb.append("0");
        }
        sb.append(tmp);
    }
    
    public static String fromShortToString(short s) {
        StringBuilder sb = new StringBuilder(6); // 0xXXXX
        appendShortAsString(s, sb);
        return sb.toString();
    }
    
    public static void appendShortAsString(short s, StringBuilder sb) {
        sb.append("0x");
        String tmp = Integer.toHexString(s & 0xFFFF).toUpperCase();
        int n_zeros = 4 - tmp.length();
        for (int i = 0; i < n_zeros; i++) {
            sb.append("0");
        }
        sb.append(tmp);
    }
}