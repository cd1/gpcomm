package br.cefetrn.smartproject.gpcomm;

/**
 * A listener that watches for insertion and removal of a smart card in a
 * terminal.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 * @see GpCommCardAdapter
 * @see GpCommTerminal#addGpCommCardListener(GpCommCardListener)
 */
public interface GpCommCardListener {
    /**
     * This method is called when a smart card is inserted into a terminal.
     * 
     * @param evt An object representing the event of insertion. It contains the
     * time the card was inserted and the card itself.
     */
    void cardInserted(GpCommCardEvent evt);
    
    /**
     * This method is called when a smart card is removed from a terminal.
     * 
     * @param evt An object representing the event of insertion. It contains the
     * time the card was inserted and the card itself.
     */
    void cardRemoved(GpCommCardEvent evt);
}