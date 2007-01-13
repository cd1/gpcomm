package br.cefetrn.smartproject.gpcomm.apdu;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CInitializeUpdate extends DefaultCApdu {
    public static final byte CLA_INITIALIZE_UPDATE = (byte) 0x80;
    public static final byte INS_INITIALIZE_UPDATE = (byte) 0x50;
    public static final byte FIRST_KEY_SET_VERSION = (byte) 0x00;
    public static final byte FIRST_KEY_INDEX = (byte) 0x00;
    
    public CInitializeUpdate(byte keySetVersion, byte keyIndex,
            byte[] hostChallenge) {
        setCla(CLA_INITIALIZE_UPDATE);
        setIns(INS_INITIALIZE_UPDATE);
        setKeySetVersion(keySetVersion);
        setKeyIndex(keyIndex);
        setHostChallenge(hostChallenge);
    }
    
    public CInitializeUpdate(byte[] hostChallenge) {
        this(FIRST_KEY_SET_VERSION, FIRST_KEY_INDEX, hostChallenge);
    }
    
    public void setKeySetVersion(byte keySetVersion) {
        setP1(keySetVersion);
    }
    
    public void setKeyIndex(byte keyIndex) {
        setP2(keyIndex);
    }
    
    public void setHostChallenge(byte[] hostChallenge) {
        setData(hostChallenge);
    }
}