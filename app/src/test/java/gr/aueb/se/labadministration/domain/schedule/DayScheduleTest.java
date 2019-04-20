package gr.aueb.se.labadministration.domain.schedule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class DayScheduleTest {

    private DaySchedule daySchedule;
    private ScheduleSlot slot;

    @Before
    public void initiate(){
        this.daySchedule = new DaySchedule(1);
        this.slot = new ScheduleSlot(new Date(), new Date(), "L", "T");
    }

    @Test
    public void getDay() {
        Assert.assertEquals(1, this.daySchedule.getDay());
    }

    @Test
    public void getSlots() {
        this.daySchedule.addSlot(this.slot);

        Assert.assertNotNull(this.daySchedule.getSlots());
    }

    @Test
    public void addSlot() {
        boolean result = this.daySchedule.addSlot(new ScheduleSlot(new Date(), new Date(), "L", "T"));

        Assert.assertTrue(result);
    }

    @Test
    public void removeSlot() {
        this.daySchedule.addSlot(this.slot);

        boolean result = this.daySchedule.removeSlot(this.slot);

        Assert.assertTrue(result);
    }
}