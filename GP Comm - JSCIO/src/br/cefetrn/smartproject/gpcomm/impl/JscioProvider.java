package br.cefetrn.smartproject.gpcomm.impl;

import br.cefetrn.smartproject.gpcomm.GpCommException;
import br.cefetrn.smartproject.gpcomm.GpCommProvider;
import br.cefetrn.smartproject.gpcomm.GpCommTerminal;
import java.util.ArrayList;
import java.util.List;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class JscioProvider implements GpCommProvider {
    private TerminalFactory terminalFactory;
    
    public JscioProvider() {
        terminalFactory = TerminalFactory.getDefault();
    }
    
    public List<GpCommTerminal> getAvailableTerminals() throws GpCommException {
        try {
            List<CardTerminal> jscio_terminals =
                    terminalFactory.terminals().list();
            List<GpCommTerminal> gpcomm_terminals =
                    new ArrayList<GpCommTerminal>(jscio_terminals.size());
            for (CardTerminal terminal : jscio_terminals) {
                gpcomm_terminals.add(new GpCommTerminalImpl(terminal));
            }
            return gpcomm_terminals;
        }
        catch (CardException e) {
            throw new GpCommException(e);
        }
    }
}