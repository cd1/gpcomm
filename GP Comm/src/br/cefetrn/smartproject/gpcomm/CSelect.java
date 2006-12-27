package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CSelect extends DefaultCApdu {
    public CSelect(byte[] aid) {
        if (aid == null) {
            throw new NullPointerException("AID can't be null");
        }
        setCla((byte) 0x00);
        setIns((byte) 0xA4);
        setP1((byte) 0x04);
        setP2((byte) 0x00);
        setData(aid);
        setLe((byte) 0x00);
    }
}
