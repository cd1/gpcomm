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
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class JscioProvider implements GpCommProvider {
    TerminalFactory jscioTerminalFactory;
    
    private static final Logger log =
            Logger.getLogger(JscioProvider.class.getName());
    private List<GpCommTerminal> gpcommTerminals;
    
    public JscioProvider() {
        jscioTerminalFactory = TerminalFactory.getDefault();
    }
    
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

    public void close() throws GpCommException {
        if (gpcommTerminals != null) {
            for (GpCommTerminal t : gpcommTerminals) {
                ((JscioGpCommTerminal) t).listenerThread.setStop();
            }
            log.info("All terminals closed.");
        }
    }
}