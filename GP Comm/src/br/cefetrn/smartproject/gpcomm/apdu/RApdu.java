package br.cefetrn.smartproject.gpcomm.apdu;

/**
 * An interface that represents a response APDU.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public interface RApdu {
    /**
     * Returns the most significant byte of the status word.
     * 
     * @return The SW1 byte.
     */
    byte getSw1();
    
    /**
     * Returns the least significant byte of the status word.
     * 
     * @return The SW2 byte.
     */
    byte getSw2();
    
    /**
     * Returns the full status word.
     * 
     * @return The SW value.
     */
    short getSw();
    
    /**
     * Returns the response data.
     * 
     * @return The data field.
     */
    byte[] getData();
}