package br.cefetrn.smartproject.gpcomm;

/**
 * @author Cr�stian Deives <cristiandeives@gmail.com>
 */
public interface RApdu {
    byte getSw1();
    
    byte getSw2();
    
    short getSw();
    
    byte[] getData();
}
