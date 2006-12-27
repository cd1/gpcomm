package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public interface RApdu {
    byte getSw1();
    
    byte getSw2();
    
    short getSw();
    
    byte[] getData();
}
