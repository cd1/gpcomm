package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public interface GpCommTerminal {
    void addGpCommCardListener(GpCommCardListener listener);
    
    void removeGpCommCardListener(GpCommCardListener listener);
    
    boolean isCardConnected() throws GpCommException;
    
    GpCommCard connect(long millis) throws GpCommException;
    
    String getName();
}