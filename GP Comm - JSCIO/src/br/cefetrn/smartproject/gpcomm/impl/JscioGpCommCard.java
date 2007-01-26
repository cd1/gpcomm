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

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 */
public class JscioGpCommCard implements GpCommCard {
    /** The actual JSCIO card object. */
    Card jscioCard;
    private static final Logger log =
            Logger.getLogger(JscioGpCommCard.class.getName());
    
    /**
     * Creates a new instance of this class.
     * 
     * @param card A valid JSCIO card object.
     */
    public JscioGpCommCard(Card card) {
        this.jscioCard = card;
    }
    
    /** {@inheritDoc} */
    @Override
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

    /** {@inheritDoc} */
    @Override
    public RApdu gpDelete(byte[] aid) throws GpCommException {
        return execute(new CDelete(aid));
    }

    /** {@inheritDoc} */
    @Override
    public RApdu gpSelect(byte[] aid) throws GpCommException {
        return execute(new CSelect(aid));
    }

    /** {@inheritDoc} */
    @Override
    public RApdu gpInstall(InstallType type, InstallData data) throws GpCommException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc} */
    @Override
    public RApdu gpLoad(boolean lastBlock, byte blockNumber, byte[] loadData) throws GpCommException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc} */
    @Override
    public RApdu gpExternalAuthenticate() throws GpCommException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc} */
    @Override
    public RApdu gpGetData() throws GpCommException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc} */
    @Override
    public RApdu gpGetStatus() throws GpCommException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc} */
    @Override
    public RApdu gpInitializeUpdate() throws GpCommException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc} */
    @Override
    public RApdu gpPutKey() throws GpCommException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc} */
    @Override
    public RApdu gpSetStatus() throws GpCommException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}