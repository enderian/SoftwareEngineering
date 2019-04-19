package gr.aueb.se.labadministration.schedule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DayScheduleTest {

    private DaySchedule daySchedule;
    private ScheduleSlot slot;

    @Before
    public void initiate(){
        this.daySchedule = new DaySchedule(1);
        this.slot = new ScheduleSlot(new Date(), new Date(), "L", "T");
        this.daySchedule.addSlot(this.slot);
    }

    @Test
    public void testCopyConstructor(){
        DaySchedule newDS = new DaySchedule(this.daySchedule);

        Assert.assertNotNull(newDS);
    }

    @Test
    public void getDay() {
        Assert.assertEquals(1, this.daySchedule.getDay());
    }

    @Test
    public void getSlots() {
        Assert.assertNotNull(this.daySchedule.getSlots());
    }

    @Test
    public void addSlot() {
        boolean result = this.daySchedule.addSlot(new ScheduleSlot(new Date(), new Date(), "L", "T"));

        Assert.assertTrue(result);
    }

    @Test
    public void removeSlot() {
        boolean result = this.daySchedule.removeSlot(this.slot);

        Assert.assertTrue(result);
    }
}