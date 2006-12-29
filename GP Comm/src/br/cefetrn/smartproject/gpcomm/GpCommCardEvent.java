package br.cefetrn.smartproject.gpcomm;

import java.util.Date;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpCommCardEvent {
    public enum Type {INSERTED, REMOVED};
    
    private Date time;
    private GpCommCard card;
    
    public GpCommCardEvent(Date time, GpCommCard card) {
        this.time = (Date) time.clone();
        this.card = card;
    }
    
    public Date getTime() {
        return (Date) time.clone();
    }
    
    public GpCommCard getCard() {
        return card;
    }
}
