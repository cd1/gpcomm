package br.cefetrn.smartproject.gpcomm;

/**
 * Signals that a smart card exception has occurred.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 */
public class GpCommException extends Exception {
    /**
     * Creates a new exception.
     * 
     * @param msg A detailed message describing the exception.
     */
    public GpCommException(String msg) {
        super(msg);
    }
    
    /**
     * Creates a new exception.
     * 
     * @param cause The exception that originnaly caused this exception.
     */
    public GpCommException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Creates a new exception.
     * 
     * @param msg A detailed message describing the exception.
     * @param cause The exception that originnaly caused this exception.
     */
    public GpCommException(String msg, Throwable cause) {
        super(msg, cause);
    }
}