package br.cefetrn.smartproject.gpcomm.apdu;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public enum CardManagerLifeCycle implements LifeCycle {
    OP_READY((byte) 0x01),
    INITIALIZED((byte) 0x07),
    SECURED((byte) 0x0F),
    CM_LOCKED((byte) 0x7F),
    TERMINATED((byte) 0xFF);
    
    private byte value;
    
    private CardManagerLifeCycle(byte value) {
        this.value = value;
    }
    
    public byte getValue() {
        return value;
    }
    
    public static CardManagerLifeCycle valueOf(byte value) {
        for (CardManagerLifeCycle cm : values()) {
            if (cm.getValue() == value) {
                return cm;
            }
        }
        return null;
    }
}