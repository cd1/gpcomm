package br.cefetrn.smartproject.gpcomm;

/**
 * @author Cr�stian Deives <cristiandeives@gmail.com>
 */
public interface GpCommCardListener {
    void cardInserted(GpCommCardEvent evt);
    
    void cardRemoved(GpCommCardEvent evt);
}
