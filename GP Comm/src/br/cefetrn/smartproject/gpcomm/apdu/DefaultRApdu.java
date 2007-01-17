package br.cefetrn.smartproject.gpcomm.apdu;

import br.cefetrn.smartproject.gpcomm.Util;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class DefaultRApdu implements RApdu {
    protected byte[] sw;
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
        this.sw = new byte[2];
        setSw(sw);
        setData(data);
    }
    
    public byte getSw1() {
        return sw[0];
    }

    public byte getSw2() {
        return sw[1];
    }

    public void setSw(short sw) {
        Util.setShort(this.sw, 0, sw);
    }
    
    public short getSw() {
        return Util.getShort(sw, 0);
    }

    public void setData(byte[] data) {
        this.data = (data == null) ? new byte[0] : data.clone();
    }
    
    public byte[] getData() {
        return data.clone();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("sw=");
        Util.appendByteArrayAsString(sw, sb);
        sb.append(",data=");
        Util.appendByteArrayAsString(data, sb);
        return sb.toString();
    }
}