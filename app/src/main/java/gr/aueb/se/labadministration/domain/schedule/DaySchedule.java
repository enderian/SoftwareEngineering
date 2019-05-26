package gr.aueb.se.labadministration.domain.schedule;

import java.io.Serializable;
import java.util.ArrayList;

public class DaySchedule implements Serializable {

    private int day;
    private ArrayList<ScheduleSlot> slots;

    public DaySchedule(int day) {
        this.day = day;
        this.slots = new ArrayList<ScheduleSlot>();
    }

    public DaySchedule(DaySchedule daySchedule) {
        this.day = daySchedule.day;
        this.slots = daySchedule.slots;
    }

    public int getDay() {
        return day;
    }

    public ArrayList<ScheduleSlot> getSlots() {
        return slots;
    }

    public boolean addSlot(ScheduleSlot slot){
        return this.slots.add(slot);
    }

    public boolean removeSlot(ScheduleSlot slot){
        return this.slots.remove(slot);
    }

}
