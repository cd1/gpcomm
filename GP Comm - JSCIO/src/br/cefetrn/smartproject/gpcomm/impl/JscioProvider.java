package br.cefetrn.smartproject.gpcomm.impl;

import br.cefetrn.smartproject.gpcomm.GpCommException;
import br.cefetrn.smartproject.gpcomm.GpCommProvider;
import br.cefetrn.smartproject.gpcomm.GpCommTerminal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

/**
 * The provider class for the Java Smart Card I/O API. This provider only works
 * in Java SE 6.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 */
public class JscioProvider implements GpCommProvider {
    /** The terminal factory. */
    TerminalFactory jscioTerminalFactory;
    private static final Logger log =
            Logger.getLogger(JscioProvider.class.getName());
    private List<GpCommTerminal> gpcommTerminals;
    
    /**
     * Creates a new instance of this class and initializes the terminal
     * factory.
     */
    public JscioProvider() {
        jscioTerminalFactory = TerminalFactory.getDefault();
    }
    
    /** {@inheritDoc} */
    @Override
    public List<GpCommTerminal> getAvailableTerminals() throws GpCommException {
        try {
            List<CardTerminal> jscio_terminals =
                    jscioTerminalFactory.terminals().list();
            int terminals_count = jscio_terminals.size();
            log.info("Found " + terminals_count + " terminal(s).");
            gpcommTerminals = new ArrayList<GpCommTerminal>(terminals_count);
            for (CardTerminal terminal : jscio_terminals) {
                JscioGpCommTerminal i = new JscioGpCommTerminal(terminal);
                gpcommTerminals.add(i);
            }
            return gpcommTerminals;
        }
        catch (CardException e) {
            throw new GpCommException(e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws GpCommException {
        if (gpcommTerminals != null) {
            for (GpCommTerminal t : gpcommTerminals) {
                ((JscioGpCommTerminal) t).listenerThread.setStop();
            }
            log.info("All terminals closed.");
        }
    }
}