package br.cefetrn.smartproject.gpcomm;

/**
 * @author Cr�stian Deives <cristiandeives@gmail.com>
 */
public class GpCommException extends Exception {
    public GpCommException(String msg) {
        super(msg);
    }
    
    public GpCommException(Throwable cause) {
        super(cause);
    }
    
    public GpCommException(String msg, Throwable cause) {
        super(msg, cause);
    }
}