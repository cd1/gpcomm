package br.cefetrn.smartproject.gpcomm.apdu;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CLoad extends DefaultCApdu {
    public static final byte CLA_LOAD = (byte) 0x80;
    public static final byte INS_LOAD = (byte) 0xE8;
    public static final byte P1_HAS_MORE_BLOCKS = (byte) 0x00;
    public static final byte P1_LAST_BLOCK = (byte) 0x80;
    public static final byte TAG_LOAD_FILE_DATA = (byte) 0xC4;
    
    public CLoad(byte number, byte[] loadFileData) {
        setCla(CLA_LOAD);
        setIns(INS_LOAD);
        setLastBlock(false);
        setBlockNumber(number);
        setLoadFileData(loadFileData);
    }
    
    public boolean isLastBlock() {
        return (p1 == P1_LAST_BLOCK);
    }
    
    public void setLastBlock(boolean last) {
        if (last) {
            setP1(P1_LAST_BLOCK);
        }
        else {
            setP1(P1_HAS_MORE_BLOCKS);
        }
    }
    
    public byte getBlockNumber() {
        return p2;
    }
    
    public void setBlockNumber(byte number) {
        setP2(number);
    }
    
    public void setLoadFileData(byte[] loadFileData) {
        byte[] new_data = new byte[2 + loadFileData.length];
        new_data[0] = TAG_LOAD_FILE_DATA;
        new_data[1] = (byte) loadFileData.length;
        System.arraycopy(loadFileData, 0, new_data, 2, loadFileData.length);
        setData(new_data);
    }
}