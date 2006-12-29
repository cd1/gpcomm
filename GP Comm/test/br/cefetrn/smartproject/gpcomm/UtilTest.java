package br.cefetrn.smartproject.gpcomm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class UtilTest {
    @Test
    public void byteToStringConversions() {
        byte b = (byte) 0x00;
        Assert.assertEquals("0x00", Util.fromByteToString(b));
        b = (byte) 0x01;
        Assert.assertEquals("0x01", Util.fromByteToString(b));
        b = (byte) 0x0A;
        Assert.assertEquals("0x0A", Util.fromByteToString(b));
        b = (byte) 0x10;
        Assert.assertEquals("0x10", Util.fromByteToString(b));
        b = (byte) 0x64;
        Assert.assertEquals("0x64", Util.fromByteToString(b));
        b = (byte) 0x7F;
        Assert.assertEquals("0x7F", Util.fromByteToString(b));
        b = (byte) 0xC8;
        Assert.assertEquals("0xC8", Util.fromByteToString(b));
        b = (byte) 0xFF;
        Assert.assertEquals("0xFF", Util.fromByteToString(b));
    }
    
    @Test
    public void byteArrayToStringConversions() {
        byte[] b = null;
        Assert.assertEquals("null", Util.fromByteArrayToString(b));
        b = new byte[] {(byte) 0x10};
        Assert.assertEquals("0x10", Util.fromByteArrayToString(b));
        b = new byte[] {(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
                (byte) 0x04};
        Assert.assertEquals("0x00 0x01 0x02 0x03 0x04",
                Util.fromByteArrayToString(b));
        b = new byte[] {(byte) 0x00, (byte) 0xA4, (byte) 0x04, (byte) 0x00,
                (byte) 0x05, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
                (byte) 0x05, (byte) 0x7F};
        Assert.assertEquals("0x00 0xA4 0x04 0x00 0x05 0x01 0x02 0x03 0x04 " +
                "0x05 0x7F", Util.fromByteArrayToString(b)); // SELECT
    }
    
    @Test
    public void shortToStringConversions() {
        short s = (short) 0x0000;
        Assert.assertEquals("0x0000", Util.fromShortToString(s));
        s = (short) 0x0001;
        Assert.assertEquals("0x0001", Util.fromShortToString(s));
        s = (short) 0x000A;
        Assert.assertEquals("0x000A", Util.fromShortToString(s));
        s = (short) 0x0010;
        Assert.assertEquals("0x0010", Util.fromShortToString(s));
        s = (short) 0x0100;
        Assert.assertEquals("0x0100", Util.fromShortToString(s));
        s = (short) 0x1000;
        Assert.assertEquals("0x1000", Util.fromShortToString(s));
        s = (short) 0x9000;
        Assert.assertEquals("0x9000", Util.fromShortToString(s));
        s = (short) 0x1234;
        Assert.assertEquals("0x1234", Util.fromShortToString(s));
        s = (short) 0x7FFF;
        Assert.assertEquals("0x7FFF", Util.fromShortToString(s));
        s = (short) 0xFFFF;
        Assert.assertEquals("0xFFFF", Util.fromShortToString(s));
    }
}
