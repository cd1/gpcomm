package br.cefetrn.smartproject.gpcomm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpComm {
    private static GpComm instance;
    private GpCommProvider provider;
    private Map<String, String> props;
    
    private GpComm() {
        Properties props_file = new Properties();
        InputStream props_file_stream = null;
        try {
            props_file_stream =
                    getClass().getResourceAsStream("gpcomm.properties");
            props_file.load(props_file_stream);
            String provider_class_name =
                    props_file.getProperty(GpCommProperties.PROVIDER);
            if (provider_class_name == null) {
                // couldn't read provider class property
            }
            else {
                Class<? extends GpCommProvider> provider_class =
                        (Class<? extends GpCommProvider>) Class.forName(
                        provider_class_name);
                provider = provider_class.newInstance();
            }
        }
        catch (IOException e) {
            // couldn't read gpcomm.properties
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
    
    public static GpComm getInstance() {
        if (instance == null) {
            instance = new GpComm();
        }
        return instance;
    }
    
    public List<GpCommTerminal> getAvailableTerminals() {
        return null;
    }
    
    public String getVersion() {
        return "GP Comm 0.1";
    }
    
    public void setProperties(Map<String, String> props) {
        this.props = props; // PENDING can't hold an external reference
    }
    
    public void setProperty(String key, String value) {
        props.put(key, value);
    }
    
    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(props);
    }
}
