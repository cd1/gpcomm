package br.cefetrn.smartproject.gpcomm;

/**
 * The interface that represents a terminal (a card reader).
 * 
 * @author Crístian Deives <cristiandeives@gmail.com>
 * @version 1.0 2007-01-29
 */
public interface GpCommTerminal {
    /**
     * Adds a listener to this terminal.
     * 
     * @param listener A GP Comm listener.
     */
    void addGpCommCardListener(GpCommCardListener listener);
    
    /**
     * Removes a listener from this terminal. The parameter {@code listener}
     * must be compared with the existing ones through the method
     * {@link java.lang.Object#equals(Object)}.
     * 
     * @param listener A GP Comm listener.
     */
    void removeGpCommCardListener(GpCommCardListener listener);
    
    /**
     * Checks if a card is inserted in this terminal.
     * 
     * @return {@code true} iif there is a card inserted in this terminal.
     */
    boolean isCardConnected() throws GpCommException;
    
    /**
     * Obtains a card from the terminal. If no card is inserted and the time
     * {@code millis} expires, this method must return null.
     * 
     * @param millis The maximum number of milliseconds that this method must
     * wait untill returning {@code null}.
     * @return A card, or {@code null} if the time expires.
     * @throws GpCommException If some error occurs while reading the card.
     */
    GpCommCard connect(long millis) throws GpCommException;
    
    /**
     * Obtains a description of this terminal.
     * 
     * @return A description which identifies the terminal.
     */
    String getName();
}