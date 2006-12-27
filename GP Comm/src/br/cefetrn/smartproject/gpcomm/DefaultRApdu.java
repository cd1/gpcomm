package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class DefaultRApdu implements RApdu {
    protected short sw;
    protected byte[] data;
    
    public DefaultRApdu() {
        sw = (short) 0x9000;
    }
    
    public DefaultRApdu(short sw) {
        this.sw = sw;
    }
    
    public DefaultRApdu(byte[] data) {
        sw = (short) 0x9000;
        this.data = data;
    }
    
    public DefaultRApdu(short sw, byte[] data) {
        this.sw = sw;
        this.data = data;
    }
    
    public byte getSw1() {
        return (byte) (sw >> 8);
    }

    public byte getSw2() {
        return (byte) (sw & 0xFF);
    }

    public void setSw(short sw) {
        this.sw = sw;
    }
    
    public short getSw() {
        return sw;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
    public byte[] getData() {
        return data;
    }
}
