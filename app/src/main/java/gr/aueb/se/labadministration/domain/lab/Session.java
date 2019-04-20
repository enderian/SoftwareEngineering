package gr.aueb.se.labadministration.domain.lab;

import gr.aueb.se.labadministration.domain.people.User;

import java.util.Date;

public class Session {

    public enum SessionStatus{STARTING, STARTED, FINISHED, INTERRUPTED}

    private Terminal terminal;
    private User user;
    private SessionStatus status;
    private Date startTime;
    private Date endTime;

    public Session(Terminal terminal, User user, SessionStatus status, Date startTime, Date endTime) {
        this.terminal = terminal;
        this.user = user;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public boolean updateSessions(){
        return this.user.registerSession(this) && this.terminal.registerSession(this);
    }
}
