package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public interface GpCommCard {
    RApdu execute(CApdu command) throws GpCommException;
    
    RApdu execute(byte[] command) throws GpCommException;
    
    RDelete gpDelete(byte[] aid) throws GpCommException;
    
    RSelect gpSelect(byte[] aid) throws GpCommException;
    
    // E os outros comandos da GlobalPlatform...
}
