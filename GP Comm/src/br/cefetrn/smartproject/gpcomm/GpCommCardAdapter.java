package br.cefetrn.smartproject.gpcomm;

/**
 * @author Cr�stian Deives <cristiandeives@gmail.com>
 */
public abstract class GpCommCardAdapter implements GpCommCardListener {
    public void cardInserted(GpCommCardEvent evt) {
        // nothing
    }

    public void cardRemoved(GpCommCardEvent evt) {
        // nothing
    }
}