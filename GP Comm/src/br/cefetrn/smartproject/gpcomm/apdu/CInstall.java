package br.cefetrn.smartproject.gpcomm.apdu;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class CInstall extends DefaultCApdu {
    public static final byte CLA_INSTALL = (byte) 0x80;
    public static final byte INS_INSTALL = (byte) 0xE6;
    
    public CInstall(InstallType type, InstallData installData) {
        setCla(CLA_INSTALL);
        setIns(INS_INSTALL);
        setType(type);
        setInstallData(installData);
    }
    
    public void setType(InstallType type) {
        if (type != null) {
            setP1(type.getValue());
        }
    }
    
    public InstallType getType() {
        return InstallType.valueOf(p1);
    }
    
    public void setInstallData(InstallData installData) {
        setData(installData.toByteArray());
    }
}