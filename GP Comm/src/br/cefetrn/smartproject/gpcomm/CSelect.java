package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CSelect extends DefaultCApdu {
    public CSelect(byte[] aid) {
        if (aid == null) {
            throw new NullPointerException("AID can't be null");
        }
        setIns((byte) 0xA4);
        setP1((byte) 0x04);
        setData(aid);
    }
    
    public RApdu execute(GpCommCard card) throws GpCommException {
        RApdu response = super.execute(card);
        RSelect response_select = new RSelect(response);
        return response_select;
    }
}