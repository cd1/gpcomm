package br.cefetrn.smartproject.gpcomm;

import java.util.Properties;

/**
 * A class with easier methods to set the GP Comm properties. All the valid GP
 * Comm properties are listed here.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 * @see GpComm
 */
public class GpCommProperties extends Properties {
    /** The property that indicates the name of the provider class. */
    public static final String PROVIDER = "gpcomm.provider";
    
    /**
     * Sets the provider class of GP Comm.
     * 
     * @param provider The provider class of GP Comm. This class must implement
     * the interface {@link GpCommProvider}.
     */
    public void setProvider(Class<? extends GpCommProvider> provider) {
        setProperty(PROVIDER, provider.getClass().getName());
    }
}