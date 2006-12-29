package br.cefetrn.smartproject.gpcomm.impl;

import br.cefetrn.smartproject.gpcomm.GpCommCardEvent;
import br.cefetrn.smartproject.gpcomm.GpCommCardEvent.Type;
import br.cefetrn.smartproject.gpcomm.GpCommCardListener;
import br.cefetrn.smartproject.gpcomm.GpCommTerminal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpCommTerminalImpl implements GpCommTerminal {
    CardTerminal jscioTerminal;
    
    private static final Logger log =
            Logger.getLogger(GpCommTerminalImpl.class.getName());
    private static final String DEFAULT_PROTOCOL = "T=0";
    private GpCommCardImpl gpcommCard;
    private List<GpCommCardListener> gpcommListeners;
    private ListenerRunner gpcommListenerThread;
    
    public GpCommTerminalImpl(CardTerminal terminal) {
        jscioTerminal = terminal;
        gpcommListeners = new ArrayList<GpCommCardListener>();
        gpcommListenerThread = new ListenerRunner();
        openTerminal();
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
    
    public void openTerminal() {
        gpcommListenerThread.start();
    }
    
    public void closeTerminal() {
        gpcommListenerThread.shouldContinue = false;
    }

    public String getName() {
        return jscioTerminal.getName();
    }

    private void fireEvent(Type type) {
        GpCommCardEvent evt = new GpCommCardEvent(new Date(), gpcommCard);
        for (GpCommCardListener l : gpcommListeners) {
            switch (type) {
                case INSERTED:
                    l.cardInserted(evt);
                    break;
                case REMOVED:
                    l.cardRemoved(evt);
                    break;
            }
        }
    }
    
    class ListenerRunner extends Thread {
        private static final int TIMEOUT = 1000; // 1 second
        private boolean shouldContinue;
        
        public ListenerRunner() {
            shouldContinue = true;
        }
        
        public void run() {
            while (shouldContinue) {
                try {
                    if (gpcommCard == null) {
                        while (jscioTerminal.waitForCardPresent(TIMEOUT)) {
                            // wait...
                        }
                        Card card = jscioTerminal.connect(DEFAULT_PROTOCOL);
                        gpcommCard = new GpCommCardImpl(card);
                        fireEvent(Type.INSERTED);
                    }
                    else {
                        while (jscioTerminal.waitForCardAbsent(TIMEOUT)) {
                            // wait...
                        }
                        fireEvent(Type.REMOVED);
                        gpcommCard.jscioCard.disconnect(true);
                        gpcommCard = null;
                    }
                }
                catch (CardException e) {
                    // something happens here
                }
            }
        }
    }
}