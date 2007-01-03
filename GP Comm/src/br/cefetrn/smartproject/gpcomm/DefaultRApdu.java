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
        this.data = (byte[]) data.clone();
    }
    
    public DefaultRApdu(short sw, byte[] data) {
        this.sw = sw;
        this.data = (byte[]) data.clone();
    }
    
    public DefaultRApdu(RApdu another_response) {
        sw = another_response.getSw();
        data = (byte[]) another_response.getData().clone();
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
        this.data = (data == null) ? null : (byte[]) data.clone();
    }
    
    public byte[] getData() {
        return (data == null) ? null : (byte[]) data.clone();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("sw=");
        Util.appendShortAsString(sw, sb);
        sb.append(",data=");
        Util.appendByteArrayAsString(data, sb);
        return sb.toString();
    }
}