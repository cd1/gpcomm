package br.cefetrn.smartproject.gpcomm.apdu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * The GlobalPlatform DELETE command.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CDelete extends DefaultCApdu {
    public static final byte CLA_DELETE = (byte) 0x80;
    public static final byte INS_DELETE = (byte) 0xE4;
    
    /**
     * Creates a new object with a specified AID.
     * 
     * @param aid The AID of the application to be deleted.
     * @throws NullPointerException if {@code aid} is {@code null}.
     */
    public CDelete(byte[] aid) {
        setCla(CLA_DELETE);
        setIns(INS_DELETE);
        setAid(aid);
    }
    
    /**
     * Returns the AID of the application to be deleted.
     * 
     * @return An AID.
     */
    public byte[] getAid() {
        byte[] new_array = new byte[data.length - 2];
        System.arraycopy(data, 2, new_array, 0, new_array.length);
        return new_array;
    }
    
    /**
     * Changes the AID of the application to be deleted.
     * 
     * @param aid The AID of the application to be deleted.
     * @throws NullPointerException if {@code aid} is {@code null}.
     */
    public void setAid(byte[] aid) {
        if (aid == null) {
            throw new NullPointerException("AID can't be null");
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write((byte) 0x4F);
        baos.write((byte) aid.length);
        try {
            baos.write(aid);
        }
        catch (IOException e) {/* it'll never catch */}
        setData(baos.toByteArray());
    }
}