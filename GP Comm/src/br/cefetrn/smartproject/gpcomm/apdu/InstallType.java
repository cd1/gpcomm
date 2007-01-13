package br.cefetrn.smartproject.gpcomm.apdu;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public enum InstallType {
    LOAD((byte) 0x02),
    INSTALL((byte) 0x04),
    MAKE_SELECTABLE((byte) 0x08),
    INSTALL_AND_MAKE_SELECTABLE((byte) 0x0C);

    private byte value;

    private InstallType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public static InstallType valueOf(byte value) {
        for (InstallType t : values()) {
            if (t.value == value) {
                return t;
            }
        }
        return null;
    }
}