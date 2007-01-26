package br.cefetrn.smartproject.gpcomm;

/**
 * An abstract adapter class for receiving smart card events. The methods in
 * this class are empty. This class exists as convenience for creating listener
 * objects.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 * @see GpCommCardListener
 */
public abstract class GpCommCardAdapter implements GpCommCardListener {
    /** {@inheritDoc} */
    @Override
    public void cardInserted(GpCommCardEvent evt) {
        // nothing
    }

    /** {@inheritDoc} */
    @Override
    public void cardRemoved(GpCommCardEvent evt) {
        // nothing
    }
}