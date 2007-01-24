package br.cefetrn.smartproject.gpcomm.apdu;

/**
 * The GlobalPlatform SELECT command.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CSelect extends DefaultCApdu {
    public static final byte CLA_SELECT = (byte) 0x00;
    public static final byte INS_SELECT = (byte) 0xA4;
    public static final byte P1_SELECT = (byte) 0x04;
    
    /**
     * Creates a new object with a specified AID.
     * 
     * @param aid The AID of the application to be selected.
     * @throws NullPointerException If {@code aid} is {@code null}.
     */
    public CSelect(byte[] aid) {
        setCla(CLA_SELECT);
        setIns(INS_SELECT);
        setP1(P1_SELECT);
        setAid(aid);
    }
    
    /**
     * Returns the AID of the application to be selected.
     * 
     * @return An AID.
     */
    public byte[] getAid() {
        return data.clone();
    }
    
    /**
     * Changes the AID of the application to be selected.
     * 
     * @param aid The AID of the application to be selected.
     */
    public void setAid(byte[] aid) {
        if (aid == null) {
            throw new NullPointerException("AID can't be null");
        }
        setData(aid.clone());
    }
}