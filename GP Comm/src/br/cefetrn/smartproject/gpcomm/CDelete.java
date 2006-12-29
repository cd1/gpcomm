package br.cefetrn.smartproject.gpcomm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CDelete extends DefaultCApdu {
    public CDelete(byte[] aid) {
        setCla((byte) 0x80);
        setIns((byte) 0xE4);
        setP2((byte) 0x00);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write((byte) 0x4F);
        baos.write((byte) aid.length);
        try {
            baos.write(aid);
        }
        catch (IOException e) {/* it'll necer catch */}
        setData(baos.toByteArray());
    }
    
    public RApdu execute(GpCommCard card) throws GpCommException {
        RApdu response = super.execute(card);
        RDelete response_delete = new RDelete(response);
        return response_delete;
    }
}
