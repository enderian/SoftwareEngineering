package gr.aueb.se.labadministration.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import gr.aueb.se.labadministration.builder.TerminalBuilder;
import gr.aueb.se.labadministration.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.lab.Laboratory;
import gr.aueb.se.labadministration.lab.Terminal;
import gr.aueb.se.labadministration.memorydao.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.people.Administrator;
import gr.aueb.se.labadministration.schedule.DaySchedule;

public class MemoryDAOTests {

    private LaboratoryDAO laboratoryDAOMemory;
    private Laboratory laboratory;
    private Terminal terminal;
    private DaySchedule daySchedule;
    private TerminalConfiguration configuration;
    private Administrator administrator;


    @Before
    public void initialize() throws UnknownHostException {

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
        this.laboratoryDAOMemory.save(this.laboratory);
    }

    @Test
    public void saveToLabDAO(){
        this.laboratoryDAOMemory.save(this.laboratory);
    }

    @Test
    public void removeLabDAO(){
        this.laboratoryDAOMemory.remove(this.laboratory);
    }

    @Test
    public void searchLabDAOReturnsResult(){
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
        ArrayList<Laboratory> labs = this.laboratoryDAOMemory.listAll();
        Assert.assertNotNull(labs);
    }
}
