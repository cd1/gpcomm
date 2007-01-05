package br.cefetrn.smartproject.gpcomm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * A default implementation of {@link CApdu}.
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @see CApdu
 */
public class DefaultCApdu implements CApdu {
    /** CLA byte. */
    protected byte cla;
    /** INS byte. */
    protected byte ins;
    /** P1 byte. */
    protected byte p1;
    /** P2 byte. */
    protected byte p2;
    /** Data field. */
    protected byte[] data;
    /** LE byte. */
    protected byte le;

    private static final Logger log =
            Logger.getLogger(DefaultCApdu.class.getName());
    
    /**
     * Creates a new command with all the bytes zeroed.
     */
    public DefaultCApdu() {
        // default
    }
    
    /**
     * Creates a new command with the specified bytes.
     * 
     * @param command A complete APDU command, in one of the 4 standard cases
     * (1: no command data, no responde data; 2: no command data, with response
     * data; 3: with command data, no response data; 4: with command data, with
     * response data). There is no problem if there are extra bytes in this
     * parameter, it'll just be logged.
     * @throws IllegalArgumentException If the length of {@code command} is less
     * than 4 or if there are less bytes in the data field than the value
     * specified in the LC field.
     */
    public DefaultCApdu(byte[] command) {
        ByteArrayInputStream bais = new ByteArrayInputStream(command);
        if (bais.available() < 4) {
            throw new IllegalArgumentException("Invalid command APDU length: " +
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
                    throw new IllegalArgumentException("Invalid LC field: " +
                            "found " + read + " bytes but was expected " + lc);
                }
                else {
                    if (bais.available() > 0) { // case 4
                        if (bais.available() == 1) {
                            setLe((byte) bais.read());
                        }
                        else {
                            if (bais.available() > 0) {
                                byte[] remaining_bytes =
                                        new byte[bais.available()];
                                System.arraycopy(command,
                                        command.length - bais.available(),
                                        remaining_bytes, 0, bais.available());
                                log.warning("There were " + bais.available() +
                                        " remaining bytes in the command " +
                                        "APDU: " + Util.fromByteArrayToString(
                                        remaining_bytes));
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Changes the class byte.
     * 
     * @param cla The CLA byte.
     */
    public void setCla(byte cla) {
        this.cla = cla;
    }
    
    public byte getCla() {
        return cla;
    }

    /**
     * Changes the instruction byte.
     * 
     * @param ins The INS byte.
     */
    public void setIns(byte ins) {
        this.ins = ins;
    }
    
    public byte getIns() {
        return ins;
    }

    /**
     * Changes the first parameter byte.
     * 
     * @param p1 The P1 byte.
     */
    public void setP1(byte p1) {
        this.p1 = p1;
    }
    
    public byte getP1() {
        return p1;
    }

    /**
     * Changes the second parameter byte.
     * 
     * @param p2 The P2 byte.
     */
    public void setP2(byte p2) {
        this.p2 = p2;
    }
    
    public byte getP2() {
        return p2;
    }

    /**
     * Returns the length of the data field.
     * 
     * @return The LC byte. This field is always calculated.
     */
    public byte getLc() {
        return (byte) ((data == null) ? 0 : data.length);
    }

    /**
     * Changes the data field.
     * 
     * @param data The data field. If {@code data.length == 0}, then the data
     * will be {@code null}.
     */
    public void setData(byte[] data) {
        this.data = (data == null || data.length == 0)
                ? null
                : (byte[]) data.clone();
    }
    
    public byte[] getData() {
        return (data == null) ? null : (byte[]) data.clone();
    }

    /**
     * Changes the number of bytes expected in the response APDU.
     * 
     * @param le The LE field.
     */
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
    
    /**
     * Returns a String that shows all the values of this command.
     * 
     * @return A String representation of this command.
     * @see Util#fromByteToString
     * @see Util#fromByteArrayToString
     */
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