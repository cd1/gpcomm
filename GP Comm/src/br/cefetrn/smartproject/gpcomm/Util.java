package br.cefetrn.smartproject.gpcomm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class Util {
    private static final Logger log = Logger.getLogger(Util.class.getName());
    
    public static String fromByteArrayToString(byte[] array) {
        if (array == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            appendByteAsString(b, sb);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1); // deletes the last space
        return sb.toString();
    }
    
    public static String fromByteToString(byte b) {
        StringBuilder sb = new StringBuilder(4); // 0xXX
        appendByteAsString(b, sb);
        return sb.toString();
    }
    
    public static void appendByteAsString(byte b, Appendable appendable) {
        try {
            String tmp = Integer.toHexString(b & 0xFF).toUpperCase();
            appendable.append("0x");
            if (tmp.length() == 1) {
                appendable.append("0");
            }
            appendable.append(tmp);
        }
        catch (IOException e) {
            log.log(Level.WARNING, "Exception while appendind a String", e);
        }
    }
    
    public static String fromShortToString(short s) {
        StringBuilder sb = new StringBuilder(6); // 0xXXXX
        sb.append("0x");
        String tmp = Integer.toHexString(s & 0xFFFF).toUpperCase();
        int n_zeros = 4 - tmp.length();
        for (int i = 0; i < n_zeros; i++) {
            sb.append("0");
        }
        sb.append(tmp);
        return sb.toString();
    }
}
