package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class GpCommCard {
    public void execute(CApdu command) {
        execute(command.toByteArray());
    }
    
    public void execute(byte[] command) {
        
    }
    
    public void close() {
        
    }
    
    public boolean isClosed() {
        return false;
    }
    
    public RDelete gpDelete(byte[] aid) {
        execute(new CDelete(aid));
        return null;
    }
    
    public RSelect gpSelect(byte[] aid) {
        execute(new CSelect(aid));
        return null;
    }
    
    // E os outros comandos da GlobalPlatform...
}
