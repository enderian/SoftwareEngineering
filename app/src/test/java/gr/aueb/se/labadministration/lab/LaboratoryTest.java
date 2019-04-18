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
        this.laboratory.addDaySchedule(daySchedule);
        this.laboratory.addAdministrator(administrator);
        this.laboratory.addTerminal(terminal);
    }

    @Test
    public void addAdministratorReturnsTrue(){

        Administrator newAdmin = new Administrator("p31xxxx", "hash", "S");
        boolean result = this.laboratory.addAdministrator(newAdmin);

        Assert.assertTrue(result);

    }

    @Test
    public void removeExistingAdminReturnsTrue(){

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
    public void removeExistingTerminalReturnsTrue() throws UnknownHostException {

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

}