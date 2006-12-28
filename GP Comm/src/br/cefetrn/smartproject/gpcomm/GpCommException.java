package br.cefetrn.smartproject.gpcomm;

import java.io.IOException;

/**
 * @author Cr�stian Deives <cristiandeives@gmail.com>
 */
public class GpCommException extends IOException {
    public GpCommException(String msg) {
        super(msg);
    }
    
    public GpCommException(Throwable cause) {
        super(cause);
    }
}
