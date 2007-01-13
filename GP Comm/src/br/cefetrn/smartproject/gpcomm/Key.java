package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class Key {
    private byte algorithm;
    private byte[] check;
    private byte[] data;
    
    public byte getAlgorithm() {
        return algorithm;
    }
    
    public void setAlgorithm(byte new_algorithm) {
        algorithm = new_algorithm;
    }
    
    public byte[] getCheckValue() {
        return check;
    }
    
    public void setCheckValue(byte[] new_check) {
        check = new_check;
    }
    
    public byte[] getDataValue() {
        return data;
    }
    
    public void setDataValue(byte[] new_data) {
        data = new_data;
    }
}