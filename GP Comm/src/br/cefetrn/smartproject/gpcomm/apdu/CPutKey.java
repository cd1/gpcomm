package br.cefetrn.smartproject.gpcomm.apdu;

import br.cefetrn.smartproject.gpcomm.Key;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CPutKey extends DefaultCApdu {
    public static final byte CLA_PUT_KEY = (byte) 0x80;
    public static final byte INS_PUT_KEY = (byte) 0xD8;
    public static final byte NEW_KEY_SET_VERSION = (byte) 0x00;
    
    public CPutKey() {
        setCla(CLA_PUT_KEY);
        setIns(INS_PUT_KEY);
        
    }
    
    public void setCurrentKeySetVersion(byte keySetVersion) {
        setP1(keySetVersion);
    }
    
    public void setKeyIndex(byte keyIndex) {
        setP2(keyIndex);
    }
    
    public void setNewKeySetVersion(byte keySetVersion) {
        // put keySetVersion in the first byte of data
    }
    
    public void addKey(Key key) { // currently it's only setting one key/command
        byte[] data = key.getDataValue();
        byte[] check = key.getCheckValue();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(key.getAlgorithm());
        baos.write((byte) data.length);
        try {
            baos.write(data);
        }
        catch (IOException e) {/* it'll never catch */}
        baos.write((byte) check.length);
        try {
            baos.write(check);
        }
        catch (IOException e) {/* it'll never catch */}
        System.arraycopy(baos.toByteArray(), 0, data, 1, baos.size());
    }
}