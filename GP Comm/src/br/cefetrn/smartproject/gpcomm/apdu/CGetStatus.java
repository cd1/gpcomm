package br.cefetrn.smartproject.gpcomm.apdu;

import br.cefetrn.smartproject.gpcomm.CardElement;
import br.cefetrn.smartproject.gpcomm.LifeCycle;
import br.cefetrn.smartproject.gpcomm.Util;
import java.util.ArrayList;
import java.util.Collection;

/**
 * //BOGUS this command can only search for an AID *OR* for a life cycle state
 * (it should be both)
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CGetStatus extends DefaultCApdu {
    public static final byte CLA_GET_STATUS = (byte) 0x80;
    public static final byte INS_GET_STATUS = (byte) 0xF2;
    public static final byte TAG_AID = (byte) 0x4F;
    public static final short TAG_LIFE_CYCLE = (short) 0x9F70;
    
    public CGetStatus(CardElement[] elements) {
        setCla(CLA_GET_STATUS);
        setIns(INS_GET_STATUS);
        setElements(elements);
    }
    
    public void setElements(CardElement... elements) {
        if (elements != null) {
            byte elements_value = (byte) 0x00;
            for (CardElement e : elements) {
                elements_value |= e.getValue();
            }
            setP1(elements_value);
        }
    }
    
    public CardElement[] getElements() {
        Collection<CardElement> elements =
                new ArrayList<CardElement>(CardElement.values().length);
        for (CardElement e : CardElement.values()) {
            if ((e.getValue() & getP1()) == e.getValue()) {
                elements.add(e);
            }
        }
        return elements.toArray(new CardElement[elements.size()]);
    }
    
    public void setLifeCycle(LifeCycle lifeCycle) {
        byte[] new_data = new byte[4]; // <tag1> <tag2> <length> <lifeCycle>
        Util.setShort(new_data, 0, TAG_LIFE_CYCLE);
        new_data[2] = (byte) 1;
        new_data[3] = lifeCycle.getValue();
        setData(new_data);
    }
    
    public void setAid(byte[] aid) {
        if (aid == null) {
            aid = new byte[0];
        }
        byte[] new_data = new byte[2 + aid.length];
        new_data[0] = TAG_AID;
        new_data[1] = (byte) aid.length;
        System.arraycopy(aid, 0, new_data, 2, aid.length);
        setData(new_data);
    }
}