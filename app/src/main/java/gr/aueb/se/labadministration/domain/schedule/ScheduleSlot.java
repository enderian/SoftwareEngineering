package gr.aueb.se.labadministration.domain.schedule;

import java.util.Date;

/**
 * The class that defines a ScheduleSlot
 */
public class ScheduleSlot {

    private Date start;
    private Date end;
    private String lesson;
    private String teacher;

    /**
     * Constructor
     * @param start of slot
     * @param end of slot
     * @param lesson of slot
     * @param teacher of slot
     */
    public ScheduleSlot(Date start, Date end, String lesson, String teacher) {
        this.start = start;
        this.end = end;
        this.lesson = lesson;
        this.teacher = teacher;
    }

    /**
     * Setters & Getters
     */
    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getLesson() {
        return lesson;
    }

    public String getTeacher() {
        return teacher;
    }
}
