package br.cefetrn.smartproject.gpcomm.apdu;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CSetStatus extends DefaultCApdu {
    public static final byte CLA_SET_STATUS = (byte) 0x80;
    public static final byte INS_SET_STATUS = (byte) 0xF0;
    
    public CSetStatus(CardElement element, LifeCycle lifeCycle, byte[] aid) {
        setCla(CLA_SET_STATUS);
        setIns(INS_SET_STATUS);
        setCardElement(element);
        setLifeCycle(lifeCycle);
        setAid(aid);
    }
    
    public void setCardElement(CardElement element) {
        if (element != null) {
            if (element == CardElement.LOAD_FILE) {
                throw new IllegalArgumentException("the status of a load " +
                        "file can't be changed.");
            }
            setP1(element.getValue());
        }
    }
    
    public CardElement getCardElement() {
        return CardElement.valueOf(p1);
    }
    
    public void setLifeCycle(LifeCycle lifeCycle) {
        CardElement element = getCardElement();
        if (element != null &&
                ((element == CardElement.APPLICATION &&
                lifeCycle instanceof ApplicationLifeCycle) ||
                (element == CardElement.CARD_MANAGER &&
                lifeCycle instanceof CardManagerLifeCycle))) {
            setP2(lifeCycle.getValue());
        }
        else {
            throw new IllegalArgumentException("a " +
                            lifeCycle.getClass().getName() + " cannot be set " +
                            "to a " + element.getClass().getName());
        }
    }
    
    public LifeCycle getLifeCycle() {
        LifeCycle life_cycle = null;
        switch (getCardElement()) {
            case APPLICATION:
                life_cycle = ApplicationLifeCycle.valueOf(p2);
                break;
            case CARD_MANAGER:
                life_cycle = CardManagerLifeCycle.valueOf(p2);
                break;
        }
        return life_cycle;
    }
    
    public void setAid(byte[] aid) {
        setData(aid);
    }
    
    public byte[] getAid() {
        return getData();
    }
}