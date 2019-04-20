package gr.aueb.se.labadministration.dao.inmemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;
import gr.aueb.se.labadministration.dao.TerminalDAO;

@RunWith(MockitoJUnitRunner.class)
public class TerminalDAOMemoryTest {

    @Mock
    private TerminalConfigurationDAO terminalConfigurationDAO = new TerminalConfigurationDAOMemory();

    @InjectMocks
    private TerminalDAO terminalDAO = new TerminalDAOMemory(terminalConfigurationDAO);

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findTerminal() {
        Assert.assertNotNull(terminalDAO.findByName("CSLAB2-11"));

        Assert.assertNull(terminalDAO.findByName("CSLAB2-13"));
    }

    @Test
    public void listAll() {
        Assert.assertFalse(terminalDAO.listAll().isEmpty());
    }

}
