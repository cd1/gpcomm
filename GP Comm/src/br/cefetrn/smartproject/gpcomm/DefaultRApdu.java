package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class DefaultRApdu implements RApdu {
    protected short sw;
    protected byte[] data;
    
    public DefaultRApdu() {
        this((short) 0x9000, null);
    }
    
    public DefaultRApdu(short sw) {
        this(sw, null);
    }
    
    public DefaultRApdu(byte[] data) {
        this((short) 0x9000, data);
    }
    
    public DefaultRApdu(RApdu another_response) {
        this(another_response.getSw(), another_response.getData());
    }
    
    public DefaultRApdu(short sw, byte[] data) {
        setSw(sw);
        setData(data);
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
        this.data = (data == null) ? new byte[0] : (byte[]) data.clone();
    }
    
    public byte[] getData() {
        return (byte[]) data.clone();
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