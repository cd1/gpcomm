package br.cefetrn.smartproject.gpcomm.impl;

import br.cefetrn.smartproject.gpcomm.GpCommException;
import br.cefetrn.smartproject.gpcomm.GpCommProvider;
import br.cefetrn.smartproject.gpcomm.GpCommTerminal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
    private List<GpCommTerminalImpl> gpcommTerminals;
    
    public JscioProvider() {
        jscioTerminalFactory = TerminalFactory.getDefault();
    }
    
    public List<GpCommTerminal> getAvailableTerminals() throws GpCommException {
        try {
            List<CardTerminal> jscio_terminals =
                    jscioTerminalFactory.terminals().list();
            gpcommTerminals =
                    new ArrayList<GpCommTerminalImpl>(jscio_terminals.size());
            List<GpCommTerminal> gpcomm_terminals_temp =
                    new ArrayList<GpCommTerminal>(jscio_terminals.size());
            for (CardTerminal terminal : jscio_terminals) {
                GpCommTerminalImpl i = new GpCommTerminalImpl(terminal);
                gpcommTerminals.add(i);
                gpcomm_terminals_temp.add(i);
            }
            return gpcomm_terminals_temp;
        }
        catch (CardException e) {
            log.log(Level.SEVERE, "Exception while loading the available " +
                    "terminals", e);
            throw new GpCommException(e);
        }
    }

    public void close() throws GpCommException {
        if (gpcommTerminals != null) {
            for (GpCommTerminalImpl t : gpcommTerminals) {
                t.listenerThread.setStop();
            }
        }
    }
}