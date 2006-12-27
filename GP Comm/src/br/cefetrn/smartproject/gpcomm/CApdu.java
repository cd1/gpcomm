package br.cefetrn.smartproject.gpcomm;

/**
 * @author Cr�stian Deives <cristiandeives@gmail.com>
 */
public interface CApdu {
    byte getCla();
    
    byte getIns();
    
    byte getP1();
    
    byte getP2();
    
    byte getLc();
    
    byte[] getData();
    
    byte getLe();
    
    byte[] toByteArray();
}
