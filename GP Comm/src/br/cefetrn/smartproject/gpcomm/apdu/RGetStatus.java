package br.cefetrn.smartproject.gpcomm.apdu;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class RGetStatus extends DefaultRApdu {
    public RGetStatus(RApdu response) {
        super(response);
    }
    
    public byte[] getAid() {
        byte[] current_data = getData();
        byte aid_length = current_data[0];
        byte[] aid = new byte[aid_length];
        System.arraycopy(current_data, 1, aid, 0, aid_length);
        return aid;
    }
    
    public LifeCycle getLifeCycle() {
        // how do I know what type of element it's being requested?
        throw new UnsupportedOperationException();
    }
    
    //XXX This method should not exist, I have to implement the above one.
    public byte getLifeCycleByte() {
        byte[] current_data = getData();
        return current_data[current_data[0] + 1];
    }
    
    public Privileges getPrivileges() {
        byte[] current_data = getData();
        return new Privileges(current_data[current_data[0] + 2]);
    }
}