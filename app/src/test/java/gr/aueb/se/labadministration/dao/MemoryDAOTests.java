package gr.aueb.se.labadministration.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Date;

import gr.aueb.se.labadministration.builder.TerminalBuilder;
import gr.aueb.se.labadministration.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.lab.Laboratory;
import gr.aueb.se.labadministration.lab.Session;
import gr.aueb.se.labadministration.lab.Terminal;
import gr.aueb.se.labadministration.memorydao.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.memorydao.SoftwarePackageDAOMemory;
import gr.aueb.se.labadministration.memorydao.TerminalConfigurationDAOMemory;
import gr.aueb.se.labadministration.memorydao.TerminalDAOMemory;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.people.Administrator;
import gr.aueb.se.labadministration.people.User;
import gr.aueb.se.labadministration.schedule.DaySchedule;

public class MemoryDAOTests {

    private LaboratoryDAO laboratoryDAOMemory;
    private Laboratory laboratory;
    private Terminal terminal;
    private DaySchedule daySchedule;
    private TerminalConfiguration configuration;
    private Administrator administrator;
    private SoftwarePackageDAO softwarePackageDAO;
    private SoftwarePackage softwarePackage;
    private TerminalConfigurationDAO terminalConfigurationDAO;
    private TerminalDAO terminalDAO;
    private UserDAO userDAO;
    private User user;
    private Session userSession, adminSession;

    @Before
    public void initialize() throws UnknownHostException {

        //Laboratory DAO Testing Initialization
        this.daySchedule = new DaySchedule(0);
        this.configuration = new TerminalConfigurationBuilder()
                .setName("T")
                .setOperatingSystem("OS")
                .setProcessor("i")
                .setstorageCapacity(2014)
                .setTotalMemory(1024)
                .createTerminalConfiguration();
        this.terminal = new TerminalBuilder()
                .setConfiguration(this.configuration)
                .setHostname("T")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setName("T")
                .setPositionX(0).setPositionY(0).createTerminal();
        this.administrator = new Administrator("p31xxxxx", "hash", "S");
        this.laboratory = new Laboratory("LAB", "LOC", true);
        this.laboratory.setOpen(true);
        this.laboratory.addDaySchedule(daySchedule);
        this.laboratory.addAdministrator(administrator);
        this.laboratory.addTerminal(terminal);
        this.laboratoryDAOMemory = new LaboratoryDAOMemory();

        //SoftwarePackage DAO Testing Initialization
        this.softwarePackageDAO = new SoftwarePackageDAOMemory();
        this.softwarePackage = new SoftwarePackage("1", "2", "3");

        //TerminalConfiguration DAO Testing Initialization
        this.terminalConfigurationDAO = new TerminalConfigurationDAOMemory();

        //Terminal DAO Testing Initialization
        this.terminalDAO = new TerminalDAOMemory();

        //User DAO Testing Initialization
        this.user = new User("p30xxxx", "h", "S");
        this.userDAO = new UserDAOMemory();
        this.userSession = new Session(this.terminal, this.user, Session.SessionStatus.FINISHED, new Date(), new Date());
        this.adminSession = new Session(this.terminal, this.administrator, Session.SessionStatus.FINISHED, new Date(), new Date());
    }

    @Test
    public void saveToLabDAO(){
        this.laboratoryDAOMemory.save(this.laboratory);
    }

    @Test
    public void removeLabDAO(){
        this.laboratoryDAOMemory.save(this.laboratory);

        this.laboratoryDAOMemory.remove(this.laboratory);
    }

    @Test
    public void searchLabDAOReturnsResult(){
        this.laboratoryDAOMemory.save(this.laboratory);

        Laboratory lab = this.laboratoryDAOMemory.findByName("LAB");
        Assert.assertNotNull(lab);
    }

    @Test
    public void searchLabDAOReturnsNull(){
        Laboratory lab = this.laboratoryDAOMemory.findByName("Test");
        Assert.assertNull(lab);
    }

    @Test
    public void listAllLabs(){
        this.laboratoryDAOMemory.save(this.laboratory);

        ArrayList<Laboratory> labs = this.laboratoryDAOMemory.listAll();
        Assert.assertNotNull(labs);
    }

    @Test
    public void saveToSPDAO(){
        this.softwarePackageDAO.save(this.softwarePackage);
    }

    @Test
    public void removeSPDAO(){
        this.softwarePackageDAO.save(this.softwarePackage);

        this.softwarePackageDAO.delete(this.softwarePackage);
    }

    @Test
    public void searchSPDAOReturnsResult(){
        this.softwarePackageDAO.save(this.softwarePackage);

        SoftwarePackage result = this.softwarePackageDAO.findByName("1");

        Assert.assertNotNull(result);
    }

    @Test
    public void searchSPDAOReturnsNull(){
        SoftwarePackage result = this.softwarePackageDAO.findByName("2");

        Assert.assertNull(result);
    }

    @Test
    public void listAllSP(){
        this.softwarePackageDAO.save(this.softwarePackage);

        ArrayList<SoftwarePackage> sps = this.softwarePackageDAO.listAll();

        Assert.assertNotNull(sps);
    }

    @Test
    public void saveToTCDAO(){
        this.terminalConfigurationDAO.save(this.configuration);
    }

    @Test
    public void deleteTCDAO(){
        this.terminalConfigurationDAO.save(this.configuration);

        this.terminalConfigurationDAO.delete(this.configuration);
    }

    @Test
    public void searchTCDAOReturnsResult(){
        this.terminalConfigurationDAO.save(this.configuration);

        TerminalConfiguration result = this.terminalConfigurationDAO.findByName("T");

        Assert.assertNotNull(result);
    }

    @Test
    public void searchTCDAOReturnsNull(){
        TerminalConfiguration result = this.terminalConfigurationDAO.findByName("Test");

        Assert.assertNull(result);
    }

    @Test
    public void listAllTCDAO(){
        this.terminalConfigurationDAO.save(this.configuration);

        ArrayList<TerminalConfiguration> configurations = this.terminalConfigurationDAO.listAll();

        Assert.assertNotNull(configurations);
    }

    @Test
    public void saveToTDAO(){
        this.terminalDAO.save(this.terminal);
    }

    @Test
    public void deleteTDAO(){
        this.terminalDAO.save(terminal);

        this.terminalDAO.delete(this.terminal);
    }

    @Test
    public void searchTDAOReturnsResult(){
        this.terminalDAO.save(terminal);

        Terminal result = this.terminalDAO.findByName("T");

        Assert.assertNotNull(result);
    }

    @Test
    public void searchTDAOReturnsNull(){
        Terminal result = this.terminalDAO.findByName("Test");

        Assert.assertNull(result);
    }

    @Test
    public void searchIPTDAOReturnsResult() throws UnknownHostException {
        this.terminalDAO.save(terminal);

        Terminal result = this.terminalDAO.findByIP(InetAddress.getByName("127.0.0.1"));

        Assert.assertNotNull(result);
    }

    @Test
    public void searchIPTDAOReturnsNull() throws UnknownHostException {
        Terminal result = this.terminalDAO.findByIP(InetAddress.getByName("192.168.1.1"));

        Assert.assertNull(result);
    }

    @Test
    public void updateTDAOStatus(){
        this.terminalDAO.save(terminal);

        this.terminalDAO.updateStatus(this.terminal, Terminal.TerminalStatus.AVAILABLE);
    }

    @Test
    public void listAllTDAO(){
        this.terminalDAO.save(terminal);

        ArrayList<Terminal> terminals = this.terminalDAO.listAll();

        Assert.assertNotNull(terminals);
    }

    @Test
    public void saveToUserDAO(){
        this.userDAO.save(this.user);
    }

    @Test
    public void deleteUserDAO(){
        this.userDAO.save(this.user);

        this.userDAO.delete(this.user);
    }

    @Test
    public void searchUserDAOReturnsResult(){
        this.userDAO.save(this.user);

        User result = this.userDAO.find("p30xxxx");

        Assert.assertNotNull(result);
    }

    @Test
    public void searchUserDAOReturnsNull(){
        User result = this.userDAO.find("Test");

        Assert.assertNull(result);
    }

    @Test
    public void listAllSessionsUserExistsInDAO(){
        this.userDAO.save(this.user);
        this.userSession.updateSessions();
        this.adminSession.updateSessions();

        ArrayList<Session> result = this.userDAO.listAllSessions(this.user);

        Assert.assertNotNull(result);
    }

    @Test
    public void listAllSessionsUserDoesNotExistsInDAO(){
        ArrayList<Session> result = this.userDAO.listAllSessions(new User("Test", "2", "2"));

        Assert.assertNull(result);
    }

    @Test
    public void listAllAdminsUserDAO(){
        this.userDAO.save(this.user);
        this.userDAO.save(this.administrator);

        ArrayList<Administrator> result = this.userDAO.listAllAdministrators();

        Assert.assertNotNull(result);
    }
}

