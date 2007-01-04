package br.cefetrn.smartproject.gpcomm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class DefaultCApdu implements CApdu {
    protected byte cla;
    protected byte ins;
    protected byte p1;
    protected byte p2;
    protected byte[] data;
    protected byte le;

    private static Logger log =
            Logger.getLogger(DefaultCApdu.class.getName());
    
    public DefaultCApdu() {
        // default
    }
    
    public DefaultCApdu(byte[] command) throws GpCommException {
        ByteArrayInputStream bais = new ByteArrayInputStream(command);
        if (bais.available() < 4) {
            throw new GpCommException("Invalid command APDU length: " +
                    bais.available());
        }
        setCla((byte) bais.read());
        setIns((byte) bais.read());
        setP1((byte) bais.read());
        setP2((byte) bais.read());
        // case 1
        if (bais.available() > 0) {
            if (bais.available() == 1) { // case 2
                setLe((byte) bais.read());
            }
            else {
                byte lc = (byte) bais.read();
                int read = bais.read(data, 0, lc);
                // case 3
                if (read < lc) {
                    throw new GpCommException("Invalid LC field: found " +
                            read + " bytes but was expected " + lc);
                }
                else {
                    if (bais.available() > 0) { // case 4
                        if (bais.available() == 1) {
                            setLe((byte) bais.read());
                        }
                        else {
                            if (bais.available() > 0) {
                                String remaining_bytes =
                                        Util.fromByteArrayToString(
                                        Arrays.copyOfRange(command,
                                        command.length - bais.available(),
                                        command.length));
                                log.warning("There were " + bais.available() +
                                        " remaining bytes in the command " +
                                        "APDU: " + remaining_bytes);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void setCla(byte cla) {
        this.cla = cla;
    }
    
    public byte getCla() {
        return cla;
    }

    public void setIns(byte ins) {
        this.ins = ins;
    }
    
    public byte getIns() {
        return ins;
    }

    public void setP1(byte p1) {
        this.p1 = p1;
    }
    
    public byte getP1() {
        return p1;
    }

    public void setP2(byte p2) {
        this.p2 = p2;
    }
    
    public byte getP2() {
        return p2;
    }

    public byte getLc() {
        return (byte) ((data == null) ? 0 : data.length);
    }

    public void setData(byte[] data) {
        this.data = (byte[]) data.clone();
    }
    
    public byte[] getData() {
        return (byte[]) data.clone();
    }

    public void setLe(byte le) {
        this.le = le;
    }
    
    public byte getLe() {
        return le;
    }
    
    public byte[] toByteArray() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(getCla());
        baos.write(getIns());
        baos.write(getP1());
        baos.write(getP2());
        if (getLc() > (byte) 0) {
            baos.write(getLc());
            try {
                baos.write(getData());
            }
            catch (IOException e) {/* it'll never catch */}
        }
        if (getLe() > 0) {
            baos.write(getLe());
        }
        return baos.toByteArray();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cla=");
        Util.appendByteAsString(cla, sb);
        sb.append(",ins=");
        Util.appendByteAsString(ins, sb);
        sb.append(",p1=");
        Util.appendByteAsString(p1, sb);
        sb.append(",p2=");
        Util.appendByteAsString(p2, sb);
        sb.append(",lc=");
        Util.appendByteAsString(getLc(), sb);
        sb.append(",data=");
        Util.appendByteArrayAsString(data, sb);
        sb.append(",le=");
        Util.appendByteAsString(le, sb);
        return sb.toString();
    }
}