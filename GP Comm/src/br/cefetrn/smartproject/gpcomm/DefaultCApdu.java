package br.cefetrn.smartproject.gpcomm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
        this.data = data;
    }
    
    public byte[] getData() {
        return data;
    }

    public void setLe(byte le) {
        this.le = le;
    }
    
    public byte getLe() {
        return le;
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
        catch (IOException e) {/* It'll never catch */}
        dump.write(getLe());
        return dump.toByteArray();
    }
}
