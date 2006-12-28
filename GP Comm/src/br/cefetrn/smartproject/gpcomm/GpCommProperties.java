package br.cefetrn.smartproject.gpcomm;

import java.util.Properties;

/**
 * @author Cr�stian Deives <cristiandeives@gmail.com>
 */
public class GpCommProperties extends Properties {
    public static final String PROVIDER = "gpcomm.provider";
    
    public void setProvider(Class<? extends GpCommProvider> provider) {
        setProperty(PROVIDER, provider.getClass().getName());
    }
}
