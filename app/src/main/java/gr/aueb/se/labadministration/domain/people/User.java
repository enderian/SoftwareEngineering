package gr.aueb.se.labadministration.domain.people;

import gr.aueb.se.labadministration.domain.lab.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User implements Cloneable  {

    private String username;
    private String passwordHash;
    private String affiliation;
    private ArrayList<Session> sessions;

    public User(String username, String passwordHash, String affiliation) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.affiliation = affiliation;
        this.sessions = new ArrayList<>();
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

    public ArrayList<Session> listSessions(){
        return this.sessions;
    }

}
