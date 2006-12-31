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
    public static final String PROPERTY_FILE_NAME = "gpcomm.properties";
    
    private static final Logger log = Logger.getLogger(GpComm.class.getName());
    private GpCommProvider provider;
    private Properties properties;
    
    public GpComm(Properties props) throws GpCommException {
        loadDefaultProperties();
        if (properties == null) {
            properties = (Properties) props.clone();
        }
        else {
            properties.putAll(props);
        }
        init();
    }
    
    public GpComm() throws GpCommException {
        loadDefaultProperties();
        init();
    }
    
    private void loadDefaultProperties() throws GpCommException {
        Properties props_file = new Properties();
        InputStream props_file_stream =
                getClass().getResourceAsStream("/" + PROPERTY_FILE_NAME);
        try {
            if (props_file_stream == null) {
                log.info("Can't find " + PROPERTY_FILE_NAME);
            }
            else {
                props_file.load(props_file_stream);
                properties = (Properties) props_file.clone();
            }
        }
        catch (IOException e) {
            throw new GpCommException("Can't read " + PROPERTY_FILE_NAME, e);
        }
        finally {
            if (props_file_stream != null) {
                try {
                    props_file_stream.close();
                }
                catch (IOException e) {
                    throw new GpCommException("Couldn't close the stream of " +
                            PROPERTY_FILE_NAME, e);
                }
            }
        }
    }
    
    private void init() throws GpCommException {
        String provider_class_name =
                properties.getProperty(GpCommProperties.PROVIDER);
        if (provider_class_name == null) {
            throw new GpCommException("Can't find the property " +
                    GpCommProperties.PROVIDER);
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
                throw new GpCommException("Can't find the class " +
                        provider_class_name, e);
            }
            catch (ClassCastException e) {
                throw new GpCommException("The class " + provider_class_name +
                        " doesn't implement the interface " +
                        GpCommProvider.class.getName(), e);
            }
            catch (IllegalAccessException e) {
                throw new GpCommException("The class " + provider_class_name +
                        " doesn't have a default constructor", e);
            }
            catch (InstantiationException e) {
                throw new GpCommException("The class " + provider_class_name +
                        "couldn't be instantiated", e);
            }
        }
    }
    
    public List<GpCommTerminal> getAvailableTerminals() throws GpCommException {
        return provider.getAvailableTerminals();
    }
    
    public Properties getProperties() {
        return (Properties) properties.clone();
    }

    public void close() throws GpCommException {
        provider.close();
    }
}