package gr.aueb.se.labadministration.dao.inmemory;

import org.junit.Assert;
import org.junit.Test;

import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;

import static org.junit.Assert.*;

public class TerminalConfigurationDAOMemoryTest {

    private TerminalConfigurationDAO terminalConfigurationDAO = new TerminalConfigurationDAOMemory();

    @Test
    public void save() {
        terminalConfigurationDAO.save( new TerminalConfigurationBuilder()
                .setName("MachineLearning")
                .setOperatingSystem("Windows")
                .setProcessor("Xeon")
                .setStorageCapacity(2014)
                .setTotalMemory(1024)
                .createTerminalConfiguration());

        Assert.assertNotNull(terminalConfigurationDAO.findByName("MachineLearning"));
    }

    @Test
    public void delete() {
        terminalConfigurationDAO.delete(terminalConfigurationDAO.findByName("Graphics"));

        Assert.assertNull(terminalConfigurationDAO.findByName("Graphics"));
    }

    @Test
    public void findByName() {
        Assert.assertNotNull(terminalConfigurationDAO.findByName("Common"));

        Assert.assertNull(terminalConfigurationDAO.findByName("Mac"));
    }

    @Test
    public void listAll() {
        Assert.assertFalse(terminalConfigurationDAO.listAll().isEmpty());
    }
}