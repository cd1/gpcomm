package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public enum CardElement {
    CARD_MANAGER((byte) 0x80),
    APPLICATION((byte) 0x40),
    LOAD_FILE((byte) 0x20);
    
    private byte value;
    
    private CardElement(byte value) {
        this.value = value;
    }
    
    public byte getValue() {
        return value;
    }
}