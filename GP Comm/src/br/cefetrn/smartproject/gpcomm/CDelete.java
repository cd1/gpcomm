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
        return data;
    }
    
    public void setAid(byte[] aid) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write((byte) 0x4F);
        baos.write((byte) aid.length);
        try {
            baos.write(aid);
        }
        catch (IOException e) {/* it'll necer catch */}
        setData(baos.toByteArray());
    }
}