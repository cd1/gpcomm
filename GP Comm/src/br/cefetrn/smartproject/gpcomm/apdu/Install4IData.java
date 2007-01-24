package br.cefetrn.smartproject.gpcomm.apdu;

public class Install4IData implements InstallData {
    private static byte[] ZERO_LENGTH_BYTE_ARRAY = new byte[0];
    private byte[] installToken;
    
    public Install4IData(byte[] executableLoadFileAid,
            byte[] innerExecutableLoadFileAid, byte[] applicationInstanceAid,
            Privileges privileges, byte[] parameterField) {
        
    }
    
    private static byte[] doClone(byte[] data) {
        return (data == null) ? ZERO_LENGTH_BYTE_ARRAY : data.clone();
    }
    
    public void setInstallToken(byte[] installToken) {
        this.installToken = doClone(installToken);
    }
    
    public byte[] getInstallToken() {
        return installToken.clone();
    }
    
    public byte[] toByteArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}