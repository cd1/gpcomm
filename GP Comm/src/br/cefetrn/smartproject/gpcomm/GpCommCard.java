package br.cefetrn.smartproject.gpcomm;

import br.cefetrn.smartproject.gpcomm.apdu.CApdu;
import br.cefetrn.smartproject.gpcomm.apdu.InstallData;
import br.cefetrn.smartproject.gpcomm.apdu.InstallType;
import br.cefetrn.smartproject.gpcomm.apdu.RApdu;

/**
 * @author Cr�stian Deives <cristiandeives@gmail.com>
 */
public interface GpCommCard {
    RApdu execute(CApdu command) throws GpCommException;
    
    RApdu gpDelete(byte[] aid) throws GpCommException;
    
    RApdu gpInstall(InstallType type, InstallData data) throws GpCommException;
    
    RApdu gpLoad(boolean lastBlock, byte blockNumber, byte[] loadData)
            throws GpCommException;
    
    RApdu gpSelect(byte[] aid) throws GpCommException;
    
    // E os outros comandos da GlobalPlatform...
}