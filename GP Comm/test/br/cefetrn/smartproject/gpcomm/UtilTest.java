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
        Assert.assertEquals("00", Util.toString(b));
        b = (byte) 0x01;
        Assert.assertEquals("01", Util.toString(b));
        b = (byte) 0x0A;
        Assert.assertEquals("0A", Util.toString(b));
        b = (byte) 0x10;
        Assert.assertEquals("10", Util.toString(b));
        b = (byte) 0x64;
        Assert.assertEquals("64", Util.toString(b));
        b = (byte) 0x7F;
        Assert.assertEquals("7F", Util.toString(b));
        b = (byte) 0xC8;
        Assert.assertEquals("C8", Util.toString(b));
        b = (byte) 0xFF;
        Assert.assertEquals("FF", Util.toString(b));
    }
    
    @Test
    public void byteArrayToStringConversions() {
        byte[] b = null;
        Assert.assertEquals("null", Util.toString(b));
        b = new byte[0];
        Assert.assertEquals("<empty>", Util.toString(b));
        b = new byte[] {(byte) 0x10};
        Assert.assertEquals("10", Util.toString(b));
        b = new byte[] {(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
                (byte) 0x04};
        Assert.assertEquals("00:01:02:03:04",
                Util.toString(b));
        b = new byte[] {(byte) 0x00, (byte) 0xA4, (byte) 0x04, (byte) 0x00,
                (byte) 0x05, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
                (byte) 0x05, (byte) 0x7F};
        Assert.assertEquals("00:A4:04:00:05:01:02:03:04:05:7F",
                Util.toString(b)); // SELECT
    }
    
    @Test
    public void shortToStringConversions() {
        short s = (short) 0x0000;
        Assert.assertEquals("0000", Util.toString(s));
        s = (short) 0x0001;
        Assert.assertEquals("0001", Util.toString(s));
        s = (short) 0x000A;
        Assert.assertEquals("000A", Util.toString(s));
        s = (short) 0x0010;
        Assert.assertEquals("0010", Util.toString(s));
        s = (short) 0x0100;
        Assert.assertEquals("0100", Util.toString(s));
        s = (short) 0x1000;
        Assert.assertEquals("1000", Util.toString(s));
        s = (short) 0x9000;
        Assert.assertEquals("9000", Util.toString(s));
        s = (short) 0x1234;
        Assert.assertEquals("1234", Util.toString(s));
        s = (short) 0x7FFF;
        Assert.assertEquals("7FFF", Util.toString(s));
        s = (short) 0xFFFF;
        Assert.assertEquals("FFFF", Util.toString(s));
    }
}