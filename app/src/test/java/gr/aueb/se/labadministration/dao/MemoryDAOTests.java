package gr.aueb.se.labadministration.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.memorydao.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.memorydao.SoftwarePackageDAOMemory;
import gr.aueb.se.labadministration.memorydao.TerminalConfigurationDAOMemory;
import gr.aueb.se.labadministration.memorydao.TerminalDAOMemory;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.domain.people.Administrator;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;

/**
 * Tests for DAOs
 */
public class MemoryDAOTests {

    private static LaboratoryDAO laboratoryDAOMemory;
    private static Laboratory laboratory;
    private static Terminal terminal;
    private static DaySchedule daySchedule;
    private static TerminalConfiguration configuration;
    private static Administrator administrator;
    private static SoftwarePackageDAO softwarePackageDAO;
    private static SoftwarePackage softwarePackage;
    private static TerminalConfigurationDAO terminalConfigurationDAO;
    private static TerminalDAO terminalDAO;
    private static UserDAO userDAO;
    private static User user;
    private static Session userSession, adminSession;

    @BeforeClass
    public static void setup() throws UnknownHostException {

        //Laboratory DAO Testing Initialization
        daySchedule = new DaySchedule(0);
        configuration = new TerminalConfigurationBuilder()
                .setName("T")
                .setOperatingSystem("OS")
                .setProcessor("i")
                .setstorageCapacity(2014)
                .setTotalMemory(1024)
                .createTerminalConfiguration();
        terminal = new TerminalBuilder()
                .setConfiguration(configuration)
                .setHostname("T")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setName("T")
                .setPositionX(0).setPositionY(0).createTerminal();
        administrator = new Administrator("p31xxxxx", "hash", "S");
        laboratory = new Laboratory("LAB", "LOC", true);
        laboratory.setOpen(true);
        laboratory.addDaySchedule(daySchedule);
        laboratory.addAdministrator(administrator);
        laboratory.addTerminal(terminal);
        laboratoryDAOMemory = new LaboratoryDAOMemory();

        //SoftwarePackage DAO Testing Initialization
        softwarePackageDAO = new SoftwarePackageDAOMemory();
        softwarePackage = new SoftwarePackage("1", "2", "3");

        //TerminalConfiguration DAO Testing Initialization
        terminalConfigurationDAO = new TerminalConfigurationDAOMemory();

        //Terminal DAO Testing Initialization
        terminalDAO = new TerminalDAOMemory();

        //User DAO Testing Initialization
        user = new User("p30xxxx", "h", "S");
        userDAO = new UserDAOMemory();
        userSession = new Session(terminal, user, Session.SessionStatus.FINISHED, new Date(), new Date());
        adminSession = new Session(terminal, administrator, Session.SessionStatus.FINISHED, new Date(), new Date());
    }

    /**
     * Test that saves Lab to Lab DAO
     */
    @Test
    public void saveToLabDAO(){
        laboratoryDAOMemory.save(laboratory);

        Assert.assertNotNull(laboratoryDAOMemory.findByName(laboratory.getName()));
    }

    /**
     * Test that removes Lab from Lab DAO
     */
    @Test
    public void removeLabDAO(){
        laboratoryDAOMemory.remove(laboratory);

        Assert.assertNull(laboratoryDAOMemory.findByName(laboratory.getName()));
    }

    /**
     * Test that searches with Lab DAO based on name and exists
     */
    @Test
    public void searchLabDAOReturnsResult(){
        laboratoryDAOMemory.save(laboratory);

        Laboratory lab = laboratoryDAOMemory.findByName("LAB");
        Assert.assertNotNull(lab);
    }

    /**
     * Test that searches with Lab DAO based on name and does not exist
     */
    @Test
    public void searchLabDAOReturnsNull(){
        Laboratory lab = laboratoryDAOMemory.findByName("Test");
        Assert.assertNull(lab);
    }

    /**
     * Test that returns non empty list of Labs from Lab DAO
     */
    @Test
    public void listAllLabs(){
        laboratoryDAOMemory.save(laboratory);

        ArrayList<Laboratory> labs = laboratoryDAOMemory.listAll();
        Assert.assertTrue(!labs.isEmpty());
    }

    /**
     * Test that saves SP to SP DAO
     */
    @Test
    public void saveToSPDAO(){
        softwarePackageDAO.save(softwarePackage);

        Assert.assertNotNull(softwarePackageDAO.findByName(softwarePackage.getName()));
    }

    /**
     * Test that removes SP from SP DAO
     */
    @Test
    public void removeSPDAO(){
        softwarePackageDAO.delete(softwarePackage);
    }

    /**
     * Test that searches with SP DAO based on name and returns result
     */
    @Test
    public void searchSPDAOReturnsResult(){
        softwarePackageDAO.save(softwarePackage);

        SoftwarePackage result = softwarePackageDAO.findByName("1");

        Assert.assertNotNull(result);
    }

    /**
     * Test that searches with SP DAO based on name and returns null
     */
    @Test
    public void searchSPDAOReturnsNull(){
        SoftwarePackage result = softwarePackageDAO.findByName("2");

        Assert.assertNull(result);
    }

    /**
     * Test that returns a non empty list of SPs
     */
    @Test
    public void listAllSP(){
        softwarePackageDAO.save(softwarePackage);

        ArrayList<SoftwarePackage> sps = softwarePackageDAO.listAll();

        Assert.assertTrue(!sps.isEmpty());
    }

    /**
     * Test that saves Terminal Config to Terminal Config DAO
     */
    @Test
    public void saveToTCDAO(){
        terminalConfigurationDAO.save(configuration);

        Assert.assertNotNull(terminalConfigurationDAO.findByName(configuration.getName()));
    }

    /**
     * Test that removes Terminal Config from Terminal Config DAO
     */
    @Test
    public void deleteTCDAO(){
        terminalConfigurationDAO.delete(configuration);

        Assert.assertNull(terminalConfigurationDAO.findByName(configuration.getName()));
    }

    /**
     * Test that searches for a Terminal config based on name and returns result
     */
    @Test
    public void searchTCDAOReturnsResult(){
        terminalConfigurationDAO.save(configuration);

        TerminalConfiguration result = terminalConfigurationDAO.findByName("T");

        Assert.assertNotNull(result);
    }

    /**
     * Test that searches for a Terminal config based on name and returns null
     */
    @Test
    public void searchTCDAOReturnsNull(){
        TerminalConfiguration result = terminalConfigurationDAO.findByName("Test");

        Assert.assertNull(result);
    }

    /**
     * Test that returns non empty list of Terminal configs
     */
    @Test
    public void listAllTCDAO(){
        terminalConfigurationDAO.save(configuration);

        Collection<TerminalConfiguration> configurations = terminalConfigurationDAO.listAll();

        Assert.assertTrue(!configurations.isEmpty());
    }

    /**
     * Test that saves Terminal to Terminal DAO
     */
    @Test
    public void saveToTDAO(){
        terminalDAO.save(terminal);

        Assert.assertNotNull(terminalDAO.findByName(terminal.getName()));
    }

    /**
     * Test that deletes Terminal from Terminal DAO
     */
    @Test
    public void deleteTDAO(){
        terminalDAO.delete(terminal);
    }

    /**
     * Test that searches for terminal based on name and returns result
     */
    @Test
    public void searchTDAOReturnsResult(){
        terminalDAO.save(terminal);

        Terminal result = terminalDAO.findByName("T");

        Assert.assertNotNull(result);
    }

    /**
     * Test that searches for terminal based on name and returns null
     */
    @Test
    public void searchTDAOReturnsNull(){
        Terminal result = terminalDAO.findByName("Test");

        Assert.assertNull(result);
    }

    /**
     * Test that searches for terminal based on IP address and returns result
     */
    @Test
    public void searchIPTDAOReturnsResult() throws UnknownHostException {
        terminalDAO.save(terminal);

        Terminal result = terminalDAO.findByIP(InetAddress.getByName("127.0.0.1"));

        Assert.assertNotNull(result);
    }

    /**
     * Test that searches for terminal based on IP address and returns null
     */
    @Test
    public void searchIPTDAOReturnsNull() throws UnknownHostException {
        Terminal result = terminalDAO.findByIP(InetAddress.getByName("192.168.1.1"));

        Assert.assertNull(result);
    }

    /**
     * Test that checks if updates to terminals happen successfully
     */
    @Test
    public void updateTDAOStatus(){
        terminalDAO.save(terminal);

        terminalDAO.updateStatus(terminal, Terminal.TerminalStatus.AVAILABLE);

        Assert.assertEquals(Terminal.TerminalStatus.AVAILABLE, terminalDAO.findByName(terminal.getName()).getStatus());
    }

    /**
     * Test that returns non empty list of terminals
     */
    @Test
    public void listAllTDAO(){
        terminalDAO.save(terminal);

        ArrayList<Terminal> terminals = terminalDAO.listAll();

        Assert.assertTrue(!terminals.isEmpty());
    }

    /**
     * Test that saves user to user DAO
     */
    @Test
    public void saveToUserDAO(){
        userDAO.save(user);

        Assert.assertNotNull(userDAO.find(user.getUsername()));
    }

    /**
     * Test that removes user from user DAO
     */
    @Test
    public void deleteUserDAO(){
        userDAO.delete(user);

        Assert.assertNull(userDAO.find(user.getUsername()));
    }

    /**
     * Test that searches user based on name and returns result
     */
    @Test
    public void searchUserDAOReturnsResult(){
        userDAO.save(user);

        User result = userDAO.find("p30xxxx");

        Assert.assertNotNull(result);
    }

    /**
     * Test that searches user based on name and returns null
     */
    @Test
    public void searchUserDAOReturnsNull(){
        User result = userDAO.find("Test");

        Assert.assertNull(result);
    }

    /**
     * Test that returns all users from DAO
     */
    @Test
    public void listAllUsers(){
        ArrayList<User> users = userDAO.listUsers();

        Assert.assertTrue(!users.isEmpty());
    }

    /**
     * Test that finds all sessions of a user and returns result
     */
    @Test
    public void listAllSessionsUserExistsInDAO(){
        userDAO.save(user);

        ArrayList<Session> result = userDAO.listAllSessions(user);

        Assert.assertNotNull(result);
    }

    /**
     * Test that finds all sessions of a user and returns null
     */
    @Test
    public void listAllSessionsUserDoesNotExistsInDAO(){
        ArrayList<Session> result = userDAO.listAllSessions(new User("Test", "2", "2"));

        Assert.assertNull(result);
    }

    /**
     * Test that returns all admins from DAO
     */
    @Test
    public void listAllAdminsUserDAO(){
        userDAO.save(user);
        userDAO.save(administrator);

        ArrayList<Administrator> result = userDAO.listAllAdministrators();

        Assert.assertNotNull(result);
    }
}

