package br.cefetrn.smartproject.gpcomm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public class Privileges {
    private Set<Privilege> privileges;
    
    public Privileges() {
        privileges = new HashSet<Privilege>(Privilege.values().length);
    }
    
    public Privileges(byte value) {
        this();
        setPrivilegesValue(value);
    }
    
    public void add(Privilege... new_privileges) {
        if (new_privileges != null) {
            for (Privilege p : privileges) {
                if (p != null) {
                    privileges.add(p);
                }
            }
        }
    }
    
    public void add(byte... new_privileges) {
        if (new_privileges != null) {
            for (byte b : new_privileges) {
                Privilege p = Privilege.valueOf(b);
                if (p != null) {
                    privileges.add(p);
                }
            }
        }
    }
    
    public void remove(Privilege... new_privileges) {
        if (new_privileges != null) {
            for (Privilege p : privileges) {
                if (p != null) {
                    privileges.remove(p);
                }
            }
        }
    }
    
    public void remove(byte... new_privileges) {
        if (new_privileges != null) {
            for (byte b : new_privileges) {
                Privilege p = Privilege.valueOf(b);
                if (p != null) {
                    privileges.remove(p);
                }
            }
        }
    }
    
    public byte getPrivilegesValue() {
        byte privileges_value = (byte) 0x00;
        for (Privilege p : privileges) {
            privileges_value |= p.getValue();
        }
        return privileges_value;
    }
    
    public void setPrivilegesValue(byte value) {
        privileges.clear();
        for (Privilege p : Privilege.values()) {
            if ((p.getValue() & value) == p.getValue()) {
                add(p);
            }
        }
    }
}