package br.cefetrn.smartproject.gpcomm;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GP Comm main class. This class is used to obtain the terminals installed on
 * the computer.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 */
public class GpComm {
    /** The name of the properties file. */
    public static final String PROPERTY_FILE_NAME = "gpcomm.properties";
    
    private static final Logger log = Logger.getLogger(GpComm.class.getName());
    private GpCommProvider provider;
    private Properties properties;
    
    /**
     * Initializes GP Comm, reading the properties from the properties file and
     * overwriting them with the specified properties.
     * 
     * @param pros Some GP Comm properties.
     * @throws GPCommException If something goes wrong while initializing GP
     * Comm.
     */
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
    
    /**
     * Initializes GP Comm, reading the properties from the properties file.
     * 
     * @throws GPCommException If something goes wrong while initializing GP
     * Comm.
     */
    public GpComm() throws GpCommException {
        loadDefaultProperties();
        init();
    }
    
    /**
     * Try to read the properties file and load it.
     */
    private void loadDefaultProperties() {
        Properties props_file = new Properties();
        InputStream props_file_stream =
                getClass().getResourceAsStream("/" + PROPERTY_FILE_NAME);
        try {
            if (props_file_stream == null) {
                log.info("Can't find " + PROPERTY_FILE_NAME);
            }
            else {
                props_file.load(props_file_stream);
                properties = props_file;
            }
        }
        catch (IOException e) {
            log.log(Level.WARNING, "Couldn't read " + PROPERTY_FILE_NAME, e);
        }
        finally {
            if (props_file_stream != null) {
                try {
                    props_file_stream.close();
                }
                catch (IOException e) {
                    log.log(Level.WARNING, "Couldn't close the stream of " +
                            PROPERTY_FILE_NAME, e);
                }
            }
        }
    }
    
    /**
     * Initialize GP Commm, based on the properties loaded.
     * 
     * @throws GPCommException If something goes wrong while initializing GP
     * Comm.
     */
    private void init() throws GpCommException {
        String provider_class_name =
                properties.getProperty(GpCommProperties.PROVIDER);
        if (provider_class_name == null) {
            throw new GpCommException("Can't find the property " +
                    GpCommProperties.PROVIDER);
        }
        else {
            log.config(GpCommProperties.PROVIDER + "=" + provider_class_name);
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
    
    /**
     * Reads all the available terminals connected to the computer.
     * 
     * @return A list of terminals, or an empty list if there is no terminal
     * available.
     * @throws GpCommException If there is an error while fecthing the
     * terminals.
     */
    public List<GpCommTerminal> getAvailableTerminals() throws GpCommException {
        return provider.getAvailableTerminals();
    }
    
    /**
     * Obtains a copy of the properties used in this instance of GP Comm.
     * 
     * @return The GP Comm properties that were used to initialize this object.
     */
    public Properties getProperties() {
        return (Properties) properties.clone();
    }

    /**
     * Finalize GP Comm and cleans its resources. This method MUST be called to
     * every instance created.
     * 
     * @throws GpCommException If there is an error while closing this GP Comm
     * instance.
     */
    public void close() throws GpCommException {
        provider.close();
    }
}