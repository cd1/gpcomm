package br.cefetrn.smartproject.gpcomm.apdu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CPutKey extends DefaultCApdu {
    public static final byte CLA_PUT_KEY = (byte) 0x80;
    public static final byte INS_PUT_KEY = (byte) 0xD8;
    public static final byte NEW_KEY_SET_VERSION = (byte) 0x00;
    
    public CPutKey(byte currentKeySetVersion, byte keyIndex,
            byte newKeySetVersion) {
        setCla(CLA_PUT_KEY);
        setIns(INS_PUT_KEY);
        setCurrentKeySetVersion(currentKeySetVersion);
        setKeyIndex(keyIndex);
        setNewKeySetVersion(newKeySetVersion);
    }
    
    public void setCurrentKeySetVersion(byte keySetVersion) {
        setP1(keySetVersion);
    }
    
    public void setKeyIndex(byte keyIndex) {
        setP2(keyIndex);
    }
    
    public void setNewKeySetVersion(byte keySetVersion) {
        if (data.length == 0) {
            byte[] new_data = new byte[1];
            data = new_data;
        }
        data[0] = keySetVersion;
    }
    
    public void addKey(Key key) {
        byte[] key_value = key.getDataValue();
        byte[] key_check = key.getCheckValue();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(key.getAlgorithm());
        baos.write((byte) key_value.length);
        try {
            baos.write(key_value);
        }
        catch (IOException e) {/* it'll never catch */}
        baos.write((byte) key_check.length);
        try {
            baos.write(key_check);
        }
        catch (IOException e) {/* it'll never catch */}
        byte[] key_bytes = baos.toByteArray();
        int key_size = key_bytes.length;
        int key_index = ensureNewKeyAndNextIndex(key_size);
        System.arraycopy(key_bytes, 0, data, key_index, key_size);
    }
    
    /**
     * Ensures that the data array can fit a new key with the specified size,
     * and returns the first index that will hold the key.
     * 
     * @param size The size of the new key. After a call of this method, the
     * data array must fit {@code size} bytes of new data.
     * @return The next index available in the data array for the new key.
     */
    private int ensureNewKeyAndNextIndex(int size) {
        int index = 1;
        int new_size = data.length + size;
        if (data.length > 1) { // although at this point data.length cannot be 0
            while (data.length - index > 0) {
                index++; // algorithm of the key
                int key_size = data[index++]; // key value length
                for (int i = 0; i < key_size; i++) {
                    index++; // key value
                }
                int check_size = data[index++]; // key check length
                for (int i = 0; i < check_size; i++) {
                    index++; // key check
                }
            }
        }
        byte[] new_data = new byte[new_size];
        System.arraycopy(data, 0, new_data, 0, data.length);
        data = new_data;
        return index;
    }
}