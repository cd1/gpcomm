package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CGetData extends DefaultCApdu {
    public CGetData(short tag) {
        setCla((byte) 0x80);
        setIns((byte) 0xCA);
        setTag(tag);
    }
    
    public short getTag() {
        return Util.makeShort(getP1(), getP2());
    }
    
    public void setTag(short tag) {
        byte[] array_tag = new byte[2];
        Util.setShort(array_tag, 0, tag);
        setP1(array_tag[0]);
        setP2(array_tag[1]);
    }
}