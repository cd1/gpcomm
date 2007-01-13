package br.cefetrn.smartproject.gpcomm;

import br.cefetrn.smartproject.gpcomm.apdu.DefaultRApdu;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class DefaultRApduTest {
    private DefaultRApdu apdu;
    
    @Before
    public void init() {
        apdu = new DefaultRApdu();
    }
    
    @Test
    public void defaultValues() {
        Assert.assertEquals((short) 0x9000, apdu.getSw());
        Assert.assertEquals(0, apdu.getData().length);
        Assert.assertEquals("sw=9000,data=<empty>", apdu.toString());
    }
    
    @Test
    public void statusWordBytes() {
        Assert.assertEquals((byte) 0x90, apdu.getSw1());
        Assert.assertEquals((byte) 0x00, apdu.getSw2());
        apdu.setSw((short) 0x1234);
        Assert.assertEquals((byte) 0x12, apdu.getSw1());
        Assert.assertEquals((byte) 0x34, apdu.getSw2());
        Assert.assertEquals("sw=1234,data=<empty>", apdu.toString());
        apdu.setSw((short) 0x0000);
        Assert.assertEquals((byte) 0x00, apdu.getSw1());
        Assert.assertEquals((byte) 0x00, apdu.getSw2());
        Assert.assertEquals("sw=0000,data=<empty>", apdu.toString());
    }
    
    @Test
    public void settersAndGetters() {
        short my_sw = (short) 0x1234;
        apdu.setSw(my_sw);
        Assert.assertEquals(my_sw, apdu.getSw());
        byte[] my_data = new byte[] {(byte) 0x01, (byte) 0x02, (byte) 0x03,
                (byte) 0x04};
        apdu.setData(my_data);
        Assert.assertTrue(Arrays.equals(my_data, apdu.getData()));
        Assert.assertEquals("sw=1234,data=01 02 03 04",
                apdu.toString());
    }
}