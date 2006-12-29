package br.cefetrn.smartproject.gpcomm;

/**
 * @author Cr�stian Deives <cristiandeives@gmail.com>
 */
public interface GpCommTerminal {
    void addGpCommCardListener(GpCommCardListener listener);
    
    void removeGpCommCardListener(GpCommCardListener listener);
    
    boolean isCardConnected();
    
    void openTerminal();
    
    void closeTerminal();
    
    String getName();
}