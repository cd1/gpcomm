package br.cefetrn.smartproject.gpcomm;

import java.util.Date;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpCommCardEvent {
    private Date time;
    private GpCommCard card;
    
    public GpCommCardEvent(Date time, GpCommCard card) {
        this.time = time;
        this.card = card;
    }
    
    public Date getTime() {
        return time;
    }
    
    public GpCommCard getCard() {
        return card;
    }
}
