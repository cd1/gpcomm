package br.cefetrn.smartproject.gpcomm.apdu;

public class RInitializeUpdate extends DefaultRApdu {
    public static final byte CARD_CHALLENGE_LENGTH = (byte) 8;
    public static final byte CARD_CRYPTOGRAM_LENGTH = (byte) 8;
    public static final byte KEY_DIVERSIFICATION_DATA_LENGTH = (byte) 10;
    public static final byte KEY_INFORMATION_DATA_LENGTH = (byte) 2;
    
    public RInitializeUpdate(RApdu response) {
        super(response);
    }
    
    public byte[] getKeyDiversificationData() {
        return subData(0, KEY_DIVERSIFICATION_DATA_LENGTH);
    }
    
    public byte[] getKeyInformationData() {
        return subData(KEY_DIVERSIFICATION_DATA_LENGTH,
                KEY_INFORMATION_DATA_LENGTH);
    }
    
    public byte[] getCardChallenge() {
        return subData(KEY_DIVERSIFICATION_DATA_LENGTH +
                KEY_INFORMATION_DATA_LENGTH, CARD_CHALLENGE_LENGTH);
    }
    
    public byte[] getCardCryptogram() {
        return subData(KEY_DIVERSIFICATION_DATA_LENGTH +
                KEY_INFORMATION_DATA_LENGTH + CARD_CHALLENGE_LENGTH,
                CARD_CRYPTOGRAM_LENGTH);
    }
    
    private byte[] subData(int offset, int length) {
        byte[] piece_of_data = new byte[length];
        System.arraycopy(getData(), offset, piece_of_data, 0, length);
        return piece_of_data;
    }
}