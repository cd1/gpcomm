package br.cefetrn.smartproject.gpcomm;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpComm {
    private static final Logger log = Logger.getLogger(GpComm.class.getName());
    private GpCommProvider provider;
    private Properties properties;
    
    public GpComm(Properties props) throws GpCommException {
        loadDefaultProperties();
        properties.putAll(props);
        init();
    }
    
    public GpComm() throws GpCommException {
        loadDefaultProperties();
        init();
    }
    
    private void loadDefaultProperties() throws GpCommException {
        Properties props_file = new Properties();
        InputStream props_file_stream = null;
        try {
            props_file_stream =
                    getClass().getResourceAsStream("gpcomm.properties");
            props_file.load(props_file_stream);
            properties = (Properties) props_file.clone();
        }
        catch (IOException e) {
            // couldn't read gpcomm.properties
        }
        finally {
            if (props_file_stream != null) {
                try {
                    props_file_stream.close();
                }
                catch (IOException e) {
                    // couldn't close the property file stream
                }
            }
        }
    }
    
    private void init() throws GpCommException {
        String provider_class_name =
                properties.getProperty(GpCommProperties.PROVIDER);
        if (provider_class_name == null) {
            // couldn't read provider class property
        }
        else {
            log.config("Found " + GpCommProperties.PROVIDER + "=" +
                    provider_class_name);
            try {
                Class<? extends GpCommProvider> provider_class =
                        (Class<? extends GpCommProvider>) Class.forName(
                        provider_class_name);
                provider = provider_class.newInstance();
            }
            catch (ClassNotFoundException e) {
                // couldn't find the provider class in the classpath
            }
            catch (ClassCastException e) {
                // the provider class doesn't implement GpCommProvider
            }
            catch (IllegalAccessException e) {
                // couldn't find the default constructor
            }
            catch (InstantiationException e) {
                // the provider class couldn't be instantiated
            }
        }
    }
    
    public List<GpCommTerminal> getAvailableTerminals() throws GpCommException {
        return provider.getAvailableTerminals();
    }
    
    public Properties getProperties() {
        return (Properties) properties.clone();
    }
}