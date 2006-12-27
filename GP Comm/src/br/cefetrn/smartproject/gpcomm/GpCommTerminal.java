package br.cefetrn.smartproject.gpcomm;

import java.util.List;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpCommTerminal {
    private List<GpCommCardListener> listeners;
    
    public void addGpCommCardListener(GpCommCardListener listener) {
        
    }
    
    public void removeGpCommCardListener(GpCommCardListener listener) {
        
    }
    
    public GpCommCard connect() {
        return null;
    }
    
    public GpCommCard connect(long millis) {
        return null;
    }
    
    public boolean isConnected() {
        return false;
    }
    
    public String getDescription() {
        return null;
    }
}
