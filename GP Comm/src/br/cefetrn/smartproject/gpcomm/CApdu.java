package br.cefetrn.smartproject.gpcomm;

/**
 * An interface that represents a command APDU.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public interface CApdu {
    /**
     * Returns the class byte of the command.
     * 
     * @return the CLA byte.
     */
    byte getCla();
    
    /**
     * Returns the byte that identify the instruction of the command.
     * 
     * @return the INS byte.
     */
    byte getIns();
    
    /**
     * Returns the first parameter of the command.
     * 
     * @return the P1 byte.
     */
    byte getP1();
    
    /**
     * Returns the second parameter of the command.
     * 
     * @return the P2 byte.
     */
    byte getP2();
    
    /**
     * Returns the number of bytes in the data field.
     * 
     * @return the LC byte.
     */
    byte getLc();
    
    /**
     * Returns the data field of the command.
     * 
     * @return the data field.
     */
    byte[] getData();
    
    /**
     * Returns the number of bytes expected in the response APDU.
     * 
     * @return the LE field.
     */
    byte getLe();
    
    /**
     * Returns the array representation of this command.
     * 
     * @return an array of bytes.
     */
    byte[] toByteArray();
}