package br.cefetrn.smartproject.gpcomm.impl;

import br.cefetrn.smartproject.gpcomm.GpCommCardEvent;
import br.cefetrn.smartproject.gpcomm.GpCommCardEvent.Type;
import br.cefetrn.smartproject.gpcomm.GpCommCardListener;
import br.cefetrn.smartproject.gpcomm.GpCommTerminal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpCommTerminalImpl implements GpCommTerminal {
    CardTerminal jscioTerminal;
    ListenerRunner listenerThread;
    
    private static final Logger log =
            Logger.getLogger(GpCommTerminalImpl.class.getName());
    private static final String DEFAULT_PROTOCOL = "T=0";
    private GpCommCardImpl gpcommCard;
    private List<GpCommCardListener> gpcommListeners;
    
    public GpCommTerminalImpl(CardTerminal terminal) {
        jscioTerminal = terminal;
        gpcommListeners = new ArrayList<GpCommCardListener>();
        listenerThread = new ListenerRunner();
        listenerThread.start();
    }
    
    public void addGpCommCardListener(GpCommCardListener listener) {
        if (listener == null) {
            throw new NullPointerException("Listener can't be null");
        }
        gpcommListeners.add(listener);
        log.finer(gpcommListeners.size() + " active listeners in " + getName());
    }

    public void removeGpCommCardListener(GpCommCardListener listener) {
        if (listener == null) {
            throw new NullPointerException("Listener can't be null");
        }
        gpcommListeners.remove(listener);
        log.finer(gpcommListeners.size() + " active listeners in " + getName());
    }

    public boolean isCardConnected() {
        return (gpcommCard != null);
    }

    public String getName() {
        return jscioTerminal.getName();
    }

    private void fireEvent(Type type) {
        GpCommCardEvent evt = new GpCommCardEvent(new Date(), gpcommCard);
        for (GpCommCardListener l : gpcommListeners) {
            switch (type) {
                case INSERTED:
                    log.finer("Card inserted.");
                    l.cardInserted(evt);
                    break;
                case REMOVED:
                    log.finer("Card removed.");
                    l.cardRemoved(evt);
                    break;
            }
        }
    }
    
    class ListenerRunner extends Thread {
        private static final int TIMEOUT = 1000; // 1 second
        private boolean stop;
        
        public void setStop() {
            stop = true;
        }
        
        public void run() {
            while (!stop) {
                try {
                    if (gpcommCard == null) {
                        while (!jscioTerminal.waitForCardPresent(TIMEOUT) &&
                                !stop) {
                            // wait...
                        }
                        if (stop) {
                            break;
                        }
                        Card card = jscioTerminal.connect(DEFAULT_PROTOCOL);
                        gpcommCard = new GpCommCardImpl(card);
                        fireEvent(Type.INSERTED);
                    }
                    else {
                        while (!jscioTerminal.waitForCardAbsent(TIMEOUT) &&
                                !stop) {
                            // wait...
                        }
                        if (stop) {
                            break;
                        }
                        fireEvent(Type.REMOVED);
                        gpcommCard.jscioCard.disconnect(true);
                        gpcommCard = null;
                    }
                }
                catch (CardException e) {
                    log.log(Level.WARNING, "Exception while listening to the " +
                            "card", e);
                }
            }
        }
    }
}