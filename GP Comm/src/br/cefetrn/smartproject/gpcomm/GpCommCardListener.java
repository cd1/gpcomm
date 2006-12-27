package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public interface GpCommCardListener {
    void cardInserted(GpCommCardEvent evt);
    
    void cardRemoved(GpCommCardEvent evt);
}
