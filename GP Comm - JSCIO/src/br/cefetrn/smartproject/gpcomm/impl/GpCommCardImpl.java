package br.cefetrn.smartproject.gpcomm.impl;

import br.cefetrn.smartproject.gpcomm.CApdu;
import br.cefetrn.smartproject.gpcomm.CDelete;
import br.cefetrn.smartproject.gpcomm.CSelect;
import br.cefetrn.smartproject.gpcomm.DefaultRApdu;
import br.cefetrn.smartproject.gpcomm.GpCommCard;
import br.cefetrn.smartproject.gpcomm.GpCommException;
import br.cefetrn.smartproject.gpcomm.RApdu;
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
        CardChannel channel = null;
        try {
            channel = jscioCard.getBasicChannel();
            ResponseAPDU response = channel.transmit(new CommandAPDU(
                    command.toByteArray()));
            return new DefaultRApdu((short) response.getSW(),
                    response.getData());
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
}