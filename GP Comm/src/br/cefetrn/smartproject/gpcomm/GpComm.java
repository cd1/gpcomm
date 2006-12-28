package br.cefetrn.smartproject.gpcomm;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpComm {
    private GpCommProvider provider;
    private Properties properties;
    
    public GpComm(Properties props) {
        loadProperties(props);
    }
    
    public GpComm() {
        Properties props_file = new Properties();
        InputStream props_file_stream = null;
        try {
            props_file_stream =
                    getClass().getResourceAsStream("gpcomm.properties");
            props_file.load(props_file_stream);
            loadProperties(props_file);
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
    
    private void loadProperties(Properties props) {
        String provider_class_name =
                props.getProperty(GpCommProperties.PROVIDER);
        if (provider_class_name == null) {
            // couldn't read provider class property
        }
        else {
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