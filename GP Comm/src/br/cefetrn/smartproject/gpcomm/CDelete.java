package br.cefetrn.smartproject.gpcomm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CDelete extends DefaultCApdu {
    public CDelete() {
        setCla((byte) 0x80);
        setIns((byte) 0xE4);
    }
    
    public CDelete(byte[] aid) {
        this();
        setAid(aid);
    }
    
    public byte[] getAid() {
        if (data == null) {
            return null;
        }
        byte[] new_array = new byte[data.length - 2];
        System.arraycopy(data, 2, new_array, 0, new_array.length);
        return new_array;
    }
    
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