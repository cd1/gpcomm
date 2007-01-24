package br.cefetrn.smartproject.gpcomm.apdu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class Install4LData implements InstallData {
    private static byte[] ZERO_LENGTH_BYTE_ARRAY = new byte[0];
    private byte[] executableLoadFileAid;
    private byte[] loadFileAid;
    private byte[] loadToken;
    private byte[] securityDomainAid;
    private byte[] parameters;
    
    
    public Install4LData(byte[] loadFileAid) {
        executableLoadFileAid = ZERO_LENGTH_BYTE_ARRAY;
        this.loadFileAid = loadFileAid;
        loadToken = ZERO_LENGTH_BYTE_ARRAY;
        securityDomainAid = ZERO_LENGTH_BYTE_ARRAY;
        parameters = ZERO_LENGTH_BYTE_ARRAY;
    }
    
    public byte[] getLoadFileAid() {
        return loadFileAid.clone();
    }
    
    public void setLoadFileAid(byte[] aid) {
        loadFileAid = (aid == null) ? ZERO_LENGTH_BYTE_ARRAY : aid.clone();
    }
    
    public byte[] getSecurityDomainAid() {
        return securityDomainAid.clone();
    }
    
    public void setSecurityDomainAid(byte[] aid) {
        securityDomainAid = (aid == null)
                ? ZERO_LENGTH_BYTE_ARRAY
                : aid.clone();
    }
    
    public byte[] getExecutableFileDap() {
        return executableLoadFileAid.clone();
    }
    
    public void setExecutableFileDap(byte[] dap) {
        executableLoadFileAid = (dap == null)
                ? ZERO_LENGTH_BYTE_ARRAY
                : dap.clone();
    }
    
    public byte[] getParameterField() {
        return parameters.clone();
    }
    
    public void setParameterField(byte[] parameters) {
        this.parameters = (parameters == null)
                ? ZERO_LENGTH_BYTE_ARRAY
                : parameters.clone();
    }
    
    public byte[] getLoadToken() {
        return loadToken.clone();
    }
    
    public void setLoadToken(byte[] loadToken) {
        this.loadToken = (loadToken == null)
                ? ZERO_LENGTH_BYTE_ARRAY
                : loadToken.clone();
    }
    
    public byte[] toByteArray() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(loadFileAid.length);
        try {
            baos.write(loadFileAid);
        }
        catch (IOException e) {/* it'll never catch */}
        baos.write(securityDomainAid.length);
        try {
            baos.write(securityDomainAid);
        }
        catch (IOException e) {/* it'll never catch */}
        baos.write(executableLoadFileAid.length);
        try {
            baos.write(executableLoadFileAid);
        }
        catch (IOException e) {/* it'll never catch */}
        baos.write(parameters.length);
        try {
            baos.write(parameters);
        }
        catch (IOException e) {/* it'll never catch */}
        baos.write(loadToken.length);
        try {
            baos.write(loadToken);
        }
        catch (IOException e) {/* it'll never catch */}
        return baos.toByteArray();
    }
}