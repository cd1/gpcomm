package br.cefetrn.smartproject.gpcomm;

import java.util.Date;

/**
 * An event which indicates an insertion or a removal of a smart card in a
 * terminal.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 * @see GpCommCardListener
 */
public class GpCommCardEvent {
    /** An enum representing the two types of events. */
    public enum Type {INSERTED, REMOVED};
    
    private Date time;
    private GpCommCard card;
    
    /**
     * Creates a new event.
     * 
     * @param time The time that the event was fired.
     * @param card The card involved in this event.
     */
    public GpCommCardEvent(Date time, GpCommCard card) {
        this.time = (Date) time.clone();
        this.card = card;
    }
    
    /**
     * Obtains the time this event was fired.
     * 
     * @return The time this event was fired.
     */
    public Date getTime() {
        return (Date) time.clone();
    }
    
    /**
     * Obtains the card associated with this event.
     * 
     * @return The object representig the card associated with this event.
     */
    public GpCommCard getCard() {
        return card;
    }
}