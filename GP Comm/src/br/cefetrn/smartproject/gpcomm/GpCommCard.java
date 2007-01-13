package br.cefetrn.smartproject.gpcomm;

import br.cefetrn.smartproject.gpcomm.apdu.CApdu;
import br.cefetrn.smartproject.gpcomm.apdu.RApdu;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public interface GpCommCard {
    RApdu execute(CApdu command) throws GpCommException;
    
    RApdu gpDelete(byte[] aid) throws GpCommException;
    
    RApdu gpSelect(byte[] aid) throws GpCommException;
    
    // E os outros comandos da GlobalPlatform...
}