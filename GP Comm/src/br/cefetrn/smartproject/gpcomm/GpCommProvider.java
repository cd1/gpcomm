package br.cefetrn.smartproject.gpcomm;

import java.util.List;

/**
 * @author Crístian Deives <cristiandeives@gmail.com>
 */
public interface GpCommProvider {
    List<GpCommTerminal> getAvailableTerminals() throws GpCommException;

    void close() throws GpCommException;
}