package br.cefetrn.smartproject.gpcomm;

/**
 * The GlobalPlatform SELECT command.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CSelect extends DefaultCApdu {    
    /**
     * Creates a new object with a specified AID.
     * 
     * @param aid The AID of the application to be selected.
     * @throws NullPointerException If {@code aid} is {@code null}.
     */
    public CSelect(byte[] aid) {
        setIns((byte) 0xA4);
        setP1((byte) 0x04);
        setAid(aid);
    }
    
    /**
     * Returns the AID of the application to be selected.
     * 
     * @return An AID.
     */
    public byte[] getAid() {
        return data;
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
        setData(aid);
    }
}