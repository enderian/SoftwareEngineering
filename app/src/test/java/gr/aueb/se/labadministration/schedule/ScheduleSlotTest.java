package gr.aueb.se.labadministration.schedule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ScheduleSlotTest {

    private ScheduleSlot slot;

    @Before
    public void initiate(){
        this.slot = new ScheduleSlot(new Date(), new Date(), "L", "T");
    }

    @Test
    public void getStart() {
        Assert.assertNotNull(this.slot.getStart());
    }

    @Test
    public void getEnd() {
        Assert.assertNotNull(this.slot.getEnd());
    }

    @Test
    public void getLesson() {
        Assert.assertEquals("L", this.slot.getLesson());
    }

    @Test
    public void getTeacher() {
        Assert.assertEquals("T", this.slot.getTeacher());
    }
}