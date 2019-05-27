package gr.aueb.se.labadministration.domain.schedule;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that defines a DaySchedule
 */
public class DaySchedule implements Serializable {

    private int day;
    private ArrayList<ScheduleSlot> slots;

    /**
     * Constructor
     * @param day of schedule
     */
    public DaySchedule(int day) {
        this.day = day;
        this.slots = new ArrayList<ScheduleSlot>();
    }

    /**
     * Copy constructor
     * @param daySchedule to copy
     */
    public DaySchedule(DaySchedule daySchedule) {
        this.day = daySchedule.day;
        this.slots = daySchedule.slots;
    }

    /**
     * Setters & Getters
     */
    public int getDay() {
        return day;
    }

    /**
     * Method that returns all slots of this DaySchedule
     * @return a list of ScheduleSlot
     */
    public ArrayList<ScheduleSlot> getSlots() {
        return slots;
    }

    /**
     * Method that adds a Slot
     * @param slot
     */
    public boolean addSlot(ScheduleSlot slot){
        return this.slots.add(slot);
    }

    /**
     * Method that removes a Slot
     * @param slot
     */
    public boolean removeSlot(ScheduleSlot slot){
        return this.slots.remove(slot);
    }

}
