package gr.aueb.se.labadministration.schedule;

import java.util.Date;

public class ScheduleSlot {

    private Date start;
    private Date end;
    private String lesson;
    private String teacher;

    public ScheduleSlot(Date start, Date end, String lesson, String teacher) {
        this.start = start;
        this.end = end;
        this.lesson = lesson;
        this.teacher = teacher;
    }

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
