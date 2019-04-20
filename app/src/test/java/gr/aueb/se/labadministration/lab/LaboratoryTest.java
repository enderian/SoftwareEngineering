package gr.aueb.se.labadministration.lab;

import gr.aueb.se.labadministration.builder.TerminalBuilder;
import gr.aueb.se.labadministration.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.people.Administrator;
import gr.aueb.se.labadministration.schedule.DaySchedule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class LaboratoryTest {

    private Laboratory laboratory;
    private DaySchedule daySchedule;
    private Terminal terminal;
    private TerminalConfiguration configuration;
    private Administrator administrator;

    @Before
    public void createVariables() throws UnknownHostException {

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
    }

    @Test
    public void addAdministratorReturnsTrue(){

        Administrator newAdmin = new Administrator("p31xxxx", "hash", "S");
        boolean result = this.laboratory.addAdministrator(newAdmin);

        Assert.assertTrue(result);

    }

    @Test
    public void removeExistingAdminReturnsTrue(){
        this.laboratory.addAdministrator(administrator);

        boolean result = this.laboratory.removeAdministrator(administrator);

        Assert.assertTrue(result);

    }

    @Test
    public void removeUnknownAdminReturnsFalse(){

        Administrator unAdmin = new Administrator("p31xxxx", "hash", "S");
        boolean result = this.laboratory.removeAdministrator(unAdmin);

        Assert.assertFalse(result);

    }

    @Test
    public void addTerminalReturnsTrue() throws UnknownHostException {

        Terminal newTerminal = new TerminalBuilder()
                .setConfiguration(this.configuration)
                .setHostname("T2")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setName("T2")
                .setPositionX(1).setPositionY(1).createTerminal();
        boolean result = this.laboratory.addTerminal(newTerminal);

        Assert.assertTrue(result);

    }

    @Test
    public void removeExistingTerminalReturnsTrue() {
        this.laboratory.addTerminal(terminal);

        boolean result = this.laboratory.removeTerminal(terminal);

        Assert.assertTrue(result);

    }

    @Test
    public void removeUnknownTerminalReturnsFalse() throws UnknownHostException {

        Terminal newTerminal = new TerminalBuilder()
                .setConfiguration(this.configuration)
                .setHostname("T2")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setName("T2")
                .setPositionX(1).setPositionY(1).createTerminal();
        boolean result = this.laboratory.removeTerminal(newTerminal);

        Assert.assertFalse(result);

    }

    @Test
    public void addNewDayScheduleReturnsTrue(){

        DaySchedule newDaySchedule = new DaySchedule(2);
        boolean result = this.laboratory.addDaySchedule(newDaySchedule);

        Assert.assertTrue(result);

    }

    @Test
    public void removeExistingDayScheduleReturnsTrue(){
        this.laboratory.addDaySchedule(daySchedule);

        boolean result = this.laboratory.removeDaySchedule(daySchedule);

        Assert.assertTrue(result);

    }

    @Test
    public void removeUnknownDaySceduleReturnsFalse(){

        DaySchedule unDaySchedule = new DaySchedule(4);
        boolean result = this.laboratory.removeDaySchedule(unDaySchedule);

        Assert.assertFalse(result);

    }

    @Test
    public void copyConstructorTest(){
        Laboratory test = new Laboratory(laboratory);

        Assert.assertNotNull(test);
    }

    @Test
    public void getName() {
        Assert.assertEquals("LAB", this.laboratory.getName());
    }

    @Test
    public void setName() {
        this.laboratory.setName("NewName");
    }

    @Test
    public void getLocation() {
        Assert.assertEquals("LOC", this.laboratory.getLocation());
    }

    @Test
    public void setLocation() {
        this.laboratory.setLocation("NewLOC");
    }

    @Test
    public void isOpen() {
        Assert.assertTrue(this.laboratory.isOpen());
    }

    @Test
    public void getSchedule() {
        Assert.assertNotNull(this.laboratory.getSchedule());
    }

    @Test
    public void setSchedule() {
        this.laboratory.setSchedule(new ArrayList<DaySchedule>());
    }

    @Test
    public void getTerminals() {
        Assert.assertNotNull(this.laboratory.getTerminals());
    }

    @Test
    public void setTerminals() {
        this.laboratory.setTerminals(new ArrayList<Terminal>());
    }

    @Test
    public void getAdministrators() {
        Assert.assertNotNull(this.laboratory.getAdministrators());
    }

    @Test
    public void setAdministrators() {
        this.laboratory.setAdministrators(new ArrayList<Administrator>());
    }
}