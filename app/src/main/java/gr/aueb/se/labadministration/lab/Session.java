package gr.aueb.se.labadministration.lab;

import java.util.Date;

public class Session {

    public enum SessionStatus{STARTING, STARTED, FINISHED, INTERRUPTED};

    private Terminal terminal;
    private SessionStatus status;
    private Date startTime;
    private Date endTime;

    public Session(Terminal terminal, SessionStatus status, Date startTime, Date endTime) {
        this.terminal = terminal;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
