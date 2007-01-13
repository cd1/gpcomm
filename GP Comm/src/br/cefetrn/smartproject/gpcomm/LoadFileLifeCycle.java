package br.cefetrn.smartproject.gpcomm;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public enum LoadFileLifeCycle implements LifeCycle {
  LOGICALLY_DELETED((byte) 0x00),
  LOADED((byte) 0x01);
  
  private byte value;
  
  private LoadFileLifeCycle(byte value) {
      this.value = value;
  }
  
  public byte getValue() {
      return value;
  }
}