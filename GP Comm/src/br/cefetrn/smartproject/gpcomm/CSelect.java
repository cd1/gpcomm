package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CSelect extends DefaultCApdu {
    public CSelect() {
        setIns((byte) 0xA4);
        setP1((byte) 0x04);
    }
    
    public CSelect(byte[] aid) {
        this();
        setAid(aid);
    }
    
    public byte[] getAid() {
        return data;
    }
    
    public void setAid(byte[] aid) {
        if (aid == null) {
            throw new NullPointerException("AID can't be null");
        }
        setData(aid);
    }
}