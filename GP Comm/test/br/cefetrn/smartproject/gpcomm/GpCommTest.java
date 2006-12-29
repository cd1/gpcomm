package br.cefetrn.smartproject.gpcomm;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GpCommTest {
    private GpComm gpcomm;
    
    @Before
    public void init() throws GpCommException {
        gpcomm = new GpComm();
    }
    
    @After
    public void close() throws GpCommException {
        gpcomm.close();
    }
    
    @Test
    public void m() throws GpCommException, InterruptedException {
        List<GpCommTerminal> terminals = gpcomm.getAvailableTerminals();
        for (GpCommTerminal t : terminals) {
            Assert.assertNotNull(t.getName());
        }
    }
}
