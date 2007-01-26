package br.cefetrn.smartproject.gpcomm;

import br.cefetrn.smartproject.gpcomm.apdu.CApdu;
import br.cefetrn.smartproject.gpcomm.apdu.InstallData;
import br.cefetrn.smartproject.gpcomm.apdu.InstallType;
import br.cefetrn.smartproject.gpcomm.apdu.RApdu;

/**
 * An interface that represents a smart card to GP Comm. One should use this
 * interface to send a command APDU to the smart card.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 */
public interface GpCommCard {
    /**
     * Executes any command APDU and returns its corresponding response.
     * 
     * @param command A command APDU. It must not be {@code null}.
     * @return The response APDU of the executed command.
     * @throws GpCommException If some error occurs when executing the command.
     */
    RApdu execute(CApdu command) throws GpCommException;
    
    /**
     * Executes the command DELETE, defined by the GlobalPlatform. It deletes an
     * application and/or an executable load file.
     * 
     * @param aid The aid of the application that will be deleted.
     * @return The response APDU of the command.
     * @throws GpCommException If some error occurs when executing the command.
     * @see br.cefetrn.smartproject.gpcomm.apdu.CDelete
     */
    RApdu gpDelete(byte[] aid) throws GpCommException;
    
    /**
     * Executes the command EXTERNAL AUTHENTICATE, defined by the
     * GlobalPlatform. It authenticates the card with the host application and
     * sets the security level of the incoming commands.
     * 
     * @return The response APDU of the command.
     * @throws GpCommException If some error occurs when executing the command.
     * @see br.cefetrn.smartproject.gpcomm.apdu.CExternalAuthenticate.
     */
    RApdu gpExternalAuthenticate() throws GpCommException;
    
    /**
     * Executes the command GET DATA, defined by the GlobalPlatform. It retrives
     * a single data object from the smart card.
     * 
     * @return The response APDU of the command.
     * @throws GpCommException If some error occurs when executing the command.
     * @see br.cefetrn.smartproject.gpcomm.apdu.CGetData
     */
    RApdu gpGetData() throws GpCommException;
    
    /**
     * Executes the command GET STATUS, defined by the GlobalPlatform. It is
     * used to obtain the life cycle state of the elements on the card, as well
     * as other useful data.
     * 
     * @return The response APDU of the command.
     * @throws GpCommException If some error occurs when executing the command.
     * @see br.cefetrn.smartproject.gpcomm.apdu.CGetStatus
     */
    RApdu gpGetStatus() throws GpCommException;
    
    /**
     * Executes the command INITIALIZE UPDATE, defined by the GlobalPlatform. It
     * exchanges data between the smart card and the host application in order
     * to initialize a secure channel.
     * 
     * @return The response APDU of the command.
     * @throws GpCommException If some error occurs when executing the command.
     * @see br.cefetrn.smartproject.gpcomm.apdu.CInitializeUpdate
     */
    RApdu gpInitializeUpdate() throws GpCommException;
    
    /**
     * Executes the command INSTALL, defined by the GlobalPlatform. It is used
     * to install an executable load file into the smart card.
     * 
     * @return The response APDU of the command.
     * @throws GpCommException If some error occurs when executing the command.
     * @see br.cefetrn.smartproject.gpcomm.apdu.CInstall
     */
    RApdu gpInstall(InstallType type, InstallData data) throws GpCommException;
    
    /**
     * Executes the command LOAD, defined by the GlobalPlatform. It loads a
     * chunk of bytes of an executable load file.
     * 
     * @param lastBlock {@code true} if this command sends the last chunk of
     * bytes of an executable load file.
     * @param blockNumber The number of this LOAD block. The first command must
     * be number 0 and there can be up to 255 blocks.
     * @param loadData A piece of data of the executable load file to be sent to
     * the smart card.
     * @return The response APDU of the command.
     * @throws GpCommException If some error occurs when executing the command.
     * @see br.cefetrn.smartproject.gpcomm.apdu.CLoad
     */
    RApdu gpLoad(boolean lastBlock, byte blockNumber, byte[] loadData)
            throws GpCommException;
    
    /**
     * Executes the command PUT KEY, defined by the GlobalPlatform. It is used
     * to replace or insert a new key set inside the smart card.
     * 
     * @return The response APDU of the command.
     * @throws GpCommException If some error occurs when executing the command.
     * @see br.cefetrn.smartproject.gpcomm.apdu.CPutKey
     */
    RApdu gpPutKey() throws GpCommException;
    
    /**
     * Executes the command SELECT, defined by the GlobalPlatform. It selects an
     * application for further use.
     * 
     * @param aid The aid of the application that will be selected.
     * @return The response APDU of the command.
     * @throws GpCommException If some error occurs when executing the command.
     * @see br.cefetrn.smartproject.gpcomm.apdu.CSelect
     */
    RApdu gpSelect(byte[] aid) throws GpCommException;
    
    /**
     * Executes the command SET STATUS, defined by the GlobalPlatform. It sets
     * the life cycle state of an element of the card.
     * 
     * @return The response APDU of the command.
     * @throws GpCommException If some error occurs when executing the command.
     * @see br.cefetrn.smartproject.gpcomm.apdu.CSetStatus
     */
    RApdu gpSetStatus() throws GpCommException;
}