package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public enum Privilege {
    SECURITY_DOMAIN((byte) 0x80),
    DAP_VERIFICATION((byte) 0x40),
    DELEGATED_MANAGEMENT((byte) 0x20),
    CARD_MANAGER_LOCK((byte) 0x10),
    CARD_TERMINATE((byte) 0x08),
    DEFAULT_SELECTED((byte) 0x04),
    PIN_CHANGE((byte) 0x02),
    MANDATED_DAP_VERIFICATION((byte) 0x01);
    
    private byte value;
    
    private Privilege(byte value) {
        this.value = value;
    }
    
    public byte getValue() {
        return value;
    }
    
    public static Privilege valueOf(byte value) {
        for (Privilege p : values()) {
            if (p.value == value) {
                return p;
            }
        }
        return null;
    }
}