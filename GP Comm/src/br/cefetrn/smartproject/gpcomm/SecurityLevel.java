package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public enum SecurityLevel {
    ENCRYPTION_AND_MAC((byte) 0x03),
    MAC((byte) 0x01),
    NO_SECUTIRY((byte) 0x00);

    private byte value;

    private SecurityLevel(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}