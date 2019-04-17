package gr.aueb.se.labadministration.people;

import gr.aueb.se.labadministration.lab.Session;

import java.util.ArrayList;

public class User {

    private String username;
    private String passwordHash;
    private String affiliation;
    private ArrayList<Session> sessions;

    public User(String username, String passwordHash, String affiliation) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.affiliation = affiliation;
        this.sessions = new ArrayList<Session>();
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public boolean registerSession(Session session){
        return this.sessions.add(session);
    }

}
