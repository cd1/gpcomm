package br.cefetrn.smartproject.gpcomm.apdu;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public enum ApplicationLifeCycle implements LifeCycle {
    LOGICALLY_DELETED((byte) 0x00),
    INSTALLED((byte) 0x03),
    SELECTABLE((byte) 0x07),
    PERSONALIZED((byte) 0x0F),
    BLOCKED((byte) 0x7F),
    LOCKED((byte) 0xFF);
    
    private byte value;
    
    private ApplicationLifeCycle(byte value) {
        this.value = value;
    }
    
    public byte getValue() {
        return value;
    }
    
    public static ApplicationLifeCycle valueOf(byte value) {
        for (ApplicationLifeCycle a : values()) {
            if (a.getValue() == value) {
                return a;
            }
        }
        return null;
    }
}