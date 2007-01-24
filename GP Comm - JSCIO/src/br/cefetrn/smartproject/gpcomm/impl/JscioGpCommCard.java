package br.cefetrn.smartproject.gpcomm.impl;

import br.cefetrn.smartproject.gpcomm.GpCommCard;
import br.cefetrn.smartproject.gpcomm.GpCommException;
import br.cefetrn.smartproject.gpcomm.apdu.CApdu;
import br.cefetrn.smartproject.gpcomm.apdu.CDelete;
import br.cefetrn.smartproject.gpcomm.apdu.CSelect;
import br.cefetrn.smartproject.gpcomm.apdu.DefaultRApdu;
import br.cefetrn.smartproject.gpcomm.apdu.InstallData;
import br.cefetrn.smartproject.gpcomm.apdu.InstallType;
import br.cefetrn.smartproject.gpcomm.apdu.RApdu;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

public class JscioGpCommCard implements GpCommCard {
    Card jscioCard;
    
    private static final Logger log =
            Logger.getLogger(JscioGpCommCard.class.getName());
    
    public JscioGpCommCard(Card card) {
        this.jscioCard = card;
    }
    
    public RApdu execute(CApdu command) throws GpCommException {
        CardChannel channel = null;
        try {
            channel = jscioCard.getBasicChannel();
            if (log.isLoggable(Level.FINE)) {
                log.fine("Command: " + command);
            }
            ResponseAPDU jscio_response = channel.transmit(new CommandAPDU(
                    command.toByteArray()));
            RApdu gpcomm_response = new DefaultRApdu(
                    (short) jscio_response.getSW(), jscio_response.getData());
            if (log.isLoggable(Level.FINE)) {
                log.fine("Response: " + gpcomm_response);
            }
            return gpcomm_response;
        }
        catch (CardException e) {
            throw new GpCommException(e);
        }
    }

    public RApdu gpDelete(byte[] aid) throws GpCommException {
        return execute(new CDelete(aid));
    }

    public RApdu gpSelect(byte[] aid) throws GpCommException {
        return execute(new CSelect(aid));
    }

    public RApdu gpInstall(InstallType type, InstallData data) throws GpCommException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RApdu gpLoad(boolean lastBlock, byte blockNumber, byte[] loadData) throws GpCommException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}