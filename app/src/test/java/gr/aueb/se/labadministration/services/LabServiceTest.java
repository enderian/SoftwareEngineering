package gr.aueb.se.labadministration.services;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

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
}