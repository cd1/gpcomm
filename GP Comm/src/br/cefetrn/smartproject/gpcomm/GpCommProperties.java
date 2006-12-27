package br.cefetrn.smartproject.gpcomm;

import java.util.HashMap;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpCommProperties extends HashMap<String, String> {
    public static final String PROVIDER = "gpcomm.provider";
    
    public void setProvider(Class<? extends GpCommProvider> provider) {
        put(PROVIDER, provider.getClass().getName());
    }
}
