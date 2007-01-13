package br.cefetrn.smartproject.gpcomm.apdu;

import br.cefetrn.smartproject.gpcomm.GpCommException;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CExternalAuthenticate extends DefaultCApdu {
    public static final byte CLA_EXTERNAL_AUTHENTICATE = (byte) 0x84;
    public static final byte INS_EXTERNAL_AUTHENTICATE = (byte) 0x82;
    public static final int CRYPTOGRAM_LENGTH = 8;
    public static final int MAC_LENGTH = 8;
    public static final int DATA_LENGTH = CRYPTOGRAM_LENGTH + MAC_LENGTH;
    
    public CExternalAuthenticate(SecurityLevel level, byte[] host_cryptogram,
            byte[] mac) throws GpCommException {
        setCla(CLA_EXTERNAL_AUTHENTICATE);
        setIns(INS_EXTERNAL_AUTHENTICATE);
        setSecurityLevel(level);
        setHostCryptogram(host_cryptogram);
        setMac(mac);
    }
    
    public void setSecurityLevel(SecurityLevel level) {
        setP1(level.getValue());
    }
    
    public SecurityLevel getSecurityLevel() {
        for (SecurityLevel l : SecurityLevel.values()) {
            if (l.getValue() == getP1()) {
                return l;
            }
        }
        return null;
    }
    
    public void setHostCryptogram(byte[] cryptogram) throws GpCommException {
        if (cryptogram.length != CRYPTOGRAM_LENGTH) {
            throw new GpCommException("The cryptogram must be exactly " +
                    CRYPTOGRAM_LENGTH + " bytes long.");
        }
        ensureExactDataLength();
        System.arraycopy(cryptogram, 0, data, 0, CRYPTOGRAM_LENGTH);
    }
    
    public byte[] getHostCrytogram() {
        ensureExactDataLength();
        byte[] cryptogram = new byte[CRYPTOGRAM_LENGTH];
        System.arraycopy(getData(), 0, cryptogram, 0, CRYPTOGRAM_LENGTH);
        return cryptogram;
    }
    
    public void setMac(byte[] mac) throws GpCommException {
        if (mac.length != MAC_LENGTH) {
            throw new GpCommException("The MAC must be exactly " + MAC_LENGTH +
                    " bytes long.");
        }
        ensureExactDataLength();
        System.arraycopy(mac, 0, data, CRYPTOGRAM_LENGTH, MAC_LENGTH);
    }
    
    public byte[] getMac() {
        ensureExactDataLength();
        byte[] mac = new byte[MAC_LENGTH];
        System.arraycopy(getData(), CRYPTOGRAM_LENGTH, mac, 0, MAC_LENGTH);
        return mac;
    }
    
    private void ensureExactDataLength() {
        int current_data_length = getData().length;
        if (current_data_length != DATA_LENGTH) {
            byte[] new_data = new byte[DATA_LENGTH];
            System.arraycopy(getData(), 0, new_data, 0,
                    Math.max(current_data_length, DATA_LENGTH));
            setData(new_data);
        }
    }
}