package gr.aueb.se.labadministration.domain.people;

import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.utilities.Password;
import gr.aueb.se.labadministration.utilities.RequestResult;

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

    public RequestResult signIn(String password) {
        if (password == null) return new RequestResult(false, "Password cannot be empty");
        if (username != null && !username.startsWith("p3") && affiliation.equals("student"))
            return new RequestResult(false, "You are not allowed to login");

        String hashedPassword = Password.hash(password);
        if(hashedPassword.equals(getPasswordHash())){
            return new RequestResult(true, "Success");
        }
        return new RequestResult(false, "Wrong password");
    }
}
