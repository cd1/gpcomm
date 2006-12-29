package br.cefetrn.smartproject.gpcomm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    
    private static final Logger log =
            Logger.getLogger(DefaultCApdu.class.getName());
    
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

    public RApdu execute(GpCommCard card) throws GpCommException {
        log.fine(toString());
        RApdu response = card.execute(toByteArray());
        log.fine(response.toString());
        return response;
    }
    
    public byte[] toByteArray() {
        ByteArrayOutputStream dump = new ByteArrayOutputStream();
        dump.write(getCla());
        dump.write(getIns());
        dump.write(getP1());
        dump.write(getP2());
        dump.write(getLc());
        try {
            dump.write(getData());
        }
        catch (IOException e) {/* it'll never catch */}
        dump.write(getLe());
        return dump.toByteArray();
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
        sb.append(Util.fromByteArrayToString(data));
        sb.append(",le=");
        Util.appendByteAsString(le, sb);
        return sb.toString();
    }
}
