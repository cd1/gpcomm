package br.cefetrn.smartproject.gpcomm;

import java.util.List;

/**
 * This is the main interface of the communication provider. The class that
 * implements this interface must have a no-arg constructor and must be set in
 * the property "gpcomm.provider" when creating a new instance of
 * {@link GpComm}.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 * @see GpComm
 */
public interface GpCommProvider {
    /**
     * Reads all the available terminals connected to the computer.
     * 
     * @return A list of terminals, or an empty list if there is no terminal
     * available.
     * @throws GpCommException If there is an error while fecthing the
     * terminals.
     */
    List<GpCommTerminal> getAvailableTerminals() throws GpCommException;

    /**
     * Finalize GP Comm and cleans its resources. This method MUST be called to
     * every instance of {@link GpComm}.
     * 
     * @throws GpCommException If there is an error while closing the GP Comm
     * instance.
     */
    void close() throws GpCommException;
}