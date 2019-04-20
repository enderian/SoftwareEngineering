package gr.aueb.se.labadministration.dao.inmemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;
import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.dao.inmemory.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.dao.inmemory.TerminalConfigurationDAOMemory;
import gr.aueb.se.labadministration.dao.inmemory.TerminalDAOMemory;
import gr.aueb.se.labadministration.dao.inmemory.UserDAOMemory;
import gr.aueb.se.labadministration.domain.builder.LaboratoryBuilder;

@RunWith(MockitoJUnitRunner.class)
public class LaboratoryDAOMemoryTest {

    @Mock
    private TerminalConfigurationDAO terminalConfigurationDAO = new TerminalConfigurationDAOMemory();

    @Mock
    private UserDAO userDAO = new UserDAOMemory();

    @InjectMocks
    private TerminalDAO terminalDAO = new TerminalDAOMemory(terminalConfigurationDAO);

    @InjectMocks
    private LaboratoryDAO laboratoryDAO = new LaboratoryDAOMemory(terminalDAO, userDAO);

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findLaboratories() {
        Assert.assertNotNull(laboratoryDAO.findByName("CSLAB-1"));

        Assert.assertNull(laboratoryDAO.findByName("CSLAB-5"));
    }

    @Test
    public void deleteLaboratories() {
        laboratoryDAO.remove(laboratoryDAO.findByName("CSLAB-2"));

        Assert.assertNull(laboratoryDAO.findByName("CSLAB-2"));
    }

    @Test
    public void saveUser() {
        laboratoryDAO.save(new LaboratoryBuilder().setName("CSLAB-3").setLocation("A45").setIsOpen(false).createLaboratory());

        Assert.assertNotNull(laboratoryDAO.findByName("CSLAB-3"));
    }

    @Test
    public void listAll() {
        Assert.assertFalse(laboratoryDAO.listAll().isEmpty());
    }

}
