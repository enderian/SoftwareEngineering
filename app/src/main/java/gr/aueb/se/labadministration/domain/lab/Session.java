package gr.aueb.se.labadministration.domain.lab;

import gr.aueb.se.labadministration.domain.people.User;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Session implements Serializable {

    public enum SessionStatus{STARTING, STARTED, FINISHED, INTERRUPTED};

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

        this.user.registerSession(this);
        this.terminal.registerSession(this);
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

    @Override
    public String toString() {
        return String.format("%s (%s): %s",
                terminal.getName(),
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(startTime),
                user.getUsername());
    }
}
