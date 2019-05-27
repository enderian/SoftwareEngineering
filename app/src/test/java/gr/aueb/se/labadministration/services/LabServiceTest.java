package gr.aueb.se.labadministration.services;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;

import static org.junit.Assert.*;

public class LabServiceTest {

    private static LabService labService;

    @BeforeClass
    public static void setup(){
        labService = new LabServiceStub();
    }

    @Test
    public void listAllLabs(){
        List<Laboratory> laboratories = labService.listLabs();

        Assert.assertNull(laboratories);
    }

    @Test
    public void listComputers(){
        List<Terminal> terminals = labService.listComputers(new Laboratory("LAB", null, true));

        Assert.assertTrue(!terminals.isEmpty());
    }

    @Test
    public void listSchedule(){
        List<DaySchedule> daySchedule = labService.listSchedule(new Laboratory("LAB", null, true));

        Assert.assertTrue(!daySchedule.isEmpty());
    }

    @Test
    public void saveTerminalTest(){
        Laboratory lab = new Laboratory("LAB", null, false);
        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setName("T")
                .setOperatingSystem("OS")
                .setProcessor("i")
                .setstorageCapacity(2014)
                .setTotalMemory(1024)
                .createTerminalConfiguration();
        Terminal terminal = null;
        try {
            terminal = new TerminalBuilder()
                    .setConfiguration(configuration)
                    .setHostname("T")
                    .setIpAddress(InetAddress.getByName("127.0.0.1"))
                    .setName("T2")
                    .setPositionX(0).setPositionY(0).createTerminal();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        labService.saveTerminal(lab, terminal);

        Assert.assertNotNull(labService.getTerminalDAO().findByName("T2"));
    }

    @Test
    public void findTerminal() {
        Terminal test = labService.findTerminal("LAB");

        Assert.assertNotNull(test);
    }

    @Test
    public void getLaboratoryDAO() {
        LaboratoryDAO dao = labService.getLaboratoryDAO();

        Assert.assertNotNull(dao);
    }

    @Test
    public void testInUse(){
        boolean isInUse = labService.isTerminalInUse(new Terminal(null, null, null, 1,1,null));

        Assert.assertTrue(isInUse);
    }

    @Test
    public void testOffline(){
        boolean isOffline = labService.isTerminalOffline(new Terminal(null, null, null, 1,1,null));

        Assert.assertFalse(isOffline);
    }

    @Test
    public void testInMaintenance(){
        boolean isInMaintenance = labService.isTerminalInMaintenance(new Terminal(null, null, null, 1,1,null));

        Assert.assertFalse(isInMaintenance);
    }

    @Test
    public void testStatus(){
        String status = labService.getStatus("t");

        Assert.assertNotNull(status);
    }

    @Test
    public void testActionSuccess(){
        boolean test = labService.terminalAction("t", Terminal.TerminalStatus.AVAILABLE);

        Assert.assertTrue(test);
    }
}