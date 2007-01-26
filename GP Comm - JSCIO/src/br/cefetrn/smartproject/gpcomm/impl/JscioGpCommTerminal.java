package br.cefetrn.smartproject.gpcomm.impl;

import br.cefetrn.smartproject.gpcomm.GpCommCard;
import br.cefetrn.smartproject.gpcomm.GpCommCardEvent;
import br.cefetrn.smartproject.gpcomm.GpCommCardEvent.Type;
import br.cefetrn.smartproject.gpcomm.GpCommCardListener;
import br.cefetrn.smartproject.gpcomm.GpCommException;
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
 * This class represents a JSCIO terminal for GP Comm.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 */
public class JscioGpCommTerminal implements GpCommTerminal {
    /** The actual terminal. */
    CardTerminal jscioTerminal;
    /** The thread that listens to this terminal. */
    ListenerRunner listenerThread;
    
    private static final Logger log =
            Logger.getLogger(JscioGpCommTerminal.class.getName());
    private static final String DEFAULT_PROTOCOL = "T=0";
    private JscioGpCommCard gpcommCard;
    private List<GpCommCardListener> gpcommListeners;
    
    /**
     * Creates a new instance of this terminal, converting a JSCIO terminal
     * to a GP Comm terminal and start the listener thread.
     * 
     * @param terminal A valid JSCIO terminal.
     */
    public JscioGpCommTerminal(CardTerminal terminal) {
        jscioTerminal = terminal;
        gpcommListeners = new ArrayList<GpCommCardListener>();
        listenerThread = new ListenerRunner();
        listenerThread.start();
    }
    
    /** {@inheritDoc} */
    @Override
    public void addGpCommCardListener(GpCommCardListener listener) {
        if (listener == null) {
            throw new NullPointerException("Listener can't be null");
        }
        gpcommListeners.add(listener);
        log.finer(gpcommListeners.size() + " active listeners in " + getName());
    }

    /** {@inheritDoc} */
    @Override
    public void removeGpCommCardListener(GpCommCardListener listener) {
        if (listener == null) {
            throw new NullPointerException("Listener can't be null");
        }
        gpcommListeners.remove(listener);
        log.finer(gpcommListeners.size() + " active listeners in " + getName());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCardConnected() throws GpCommException {
        try {
            return jscioTerminal.isCardPresent();
        }
        catch (CardException e) {
            throw new GpCommException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public GpCommCard connect(long millis) throws GpCommException {
        try {
            if (jscioTerminal.waitForCardPresent(millis)) {
                Card card = jscioTerminal.connect(DEFAULT_PROTOCOL);
                return new JscioGpCommCard(card);
            }
            else {
                return null;
            }
        }
        catch (CardException e) {
            throw new GpCommException(e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
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
    
    /**
     * The thread that listens to a terminal. It keeps executing untill the
     * method {@link br.cefetrn.smartproject.gpcomm.GpComm#close()} is called.
     */
    class ListenerRunner extends Thread {
        public static final int TIMEOUT = 1000; // 1 second
        private boolean stop;
        
        /**
         * Tells this thread to stop running.
         */
        public void setStop() {
            stop = true;
        }
        
        /**
         * Keeps waiting for insertion and removal of a card in this terminal.
         * After one of these events happens, the corresponding methods in the
         * available listeners are called.
         * It'll only stop with a call to the method {@link setStop()}.
         */
        @Override
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
                        gpcommCard = new JscioGpCommCard(card);
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
                        gpcommCard = null;
                    }
                }
                catch (CardException e) {
                    log.log(Level.WARNING, "Exception while listening to the " +
                            "card", e);
                }
            }
            if (gpcommCard != null) {
                try {
                    gpcommCard.jscioCard.disconnect(true);
                    gpcommCard = null;
                }
                catch (CardException e) {
                    log.log(Level.WARNING, "Exception while stopping to " +
                            "listen to the card", e);
                }
            }
        }
    }
}