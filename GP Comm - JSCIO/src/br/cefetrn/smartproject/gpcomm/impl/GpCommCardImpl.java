package br.cefetrn.smartproject.gpcomm.impl;

import br.cefetrn.smartproject.gpcomm.CApdu;
import br.cefetrn.smartproject.gpcomm.CDelete;
import br.cefetrn.smartproject.gpcomm.CSelect;
import br.cefetrn.smartproject.gpcomm.DefaultRApdu;
import br.cefetrn.smartproject.gpcomm.GpCommCard;
import br.cefetrn.smartproject.gpcomm.GpCommException;
import br.cefetrn.smartproject.gpcomm.RApdu;
import br.cefetrn.smartproject.gpcomm.RDelete;
import br.cefetrn.smartproject.gpcomm.RSelect;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpCommCardImpl implements GpCommCard {
    Card jscioCard;
    
    public GpCommCardImpl(Card card) {
        this.jscioCard = card;
    }
    
    public RApdu execute(CApdu command) throws GpCommException {
        return execute(command.toByteArray());
    }

    public RApdu execute(byte[] command) throws GpCommException {
        CardChannel channel = null;
        try {
            channel = jscioCard.openLogicalChannel();
            ResponseAPDU response = channel.transmit(new CommandAPDU(command));
            return new DefaultRApdu((short) response.getSW(),
                    response.getData());
        }
        catch (CardException e) {
            throw new GpCommException(e);
        }
        finally {
            if (channel != null) {
                try {
                    channel.close();
                }
                catch (CardException e) {
                    throw new GpCommException(e);
                }
            }
        }
    }

    public RDelete gpDelete(byte[] aid) throws GpCommException {
        return executeInternal(new CDelete(aid));
    }

    public RSelect gpSelect(byte[] aid) throws GpCommException {
        return executeInternal(new CSelect(aid));
    }
    
    private <T extends RApdu> T executeInternal(CApdu c)
            throws GpCommException {
        return (T) c.execute(this);
    }
}