package br.cefetrn.smartproject.gpcomm.apdu;

import br.cefetrn.smartproject.gpcomm.Util;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CGetData extends DefaultCApdu {
    public static final byte CLA_GET_DATA = (byte) 0x80;
    public static final byte INS_GET_DATA = (byte) 0xCA;
    
    public CGetData(short tag) {
        setCla(CLA_GET_DATA);
        setIns(INS_GET_DATA);
        setTag(tag);
    }
    
    public short getTag() {
        return Util.makeShort(getP1(), getP2());
    }
    
    public void setTag(short tag) {
        byte[] array_tag = new byte[2]; // the size of a short
        Util.setShort(array_tag, 0, tag);
        setP1(array_tag[0]);
        setP2(array_tag[1]);
    }
}