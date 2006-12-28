package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CDelete extends DefaultCApdu {
    public CDelete(byte[] aid) {
        // don't remember the bytes
    }
    
    public RApdu execute(GpCommCard card) throws GpCommException {
        RApdu response = super.execute(card);
        RDelete response_delete = new RDelete(response);
        return response_delete;
    }
}
