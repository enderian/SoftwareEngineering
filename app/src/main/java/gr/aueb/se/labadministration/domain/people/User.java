package gr.aueb.se.labadministration.domain.people;

import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.utilities.Password;
import gr.aueb.se.labadministration.utilities.RequestResult;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that defines a User
 */
public class User implements Serializable {

    private String username;
    private String passwordHash;
    private String affiliation;
    private ArrayList<Session> sessions;

    /**
     * Constructor
     * @param username of user
     * @param passwordHash of user
     * @param affiliation of user
     */
    public User(String username, String passwordHash, String affiliation) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.affiliation = affiliation;
        this.sessions = new ArrayList<>();
    }

    /**
     * Setters & Getters
     */
    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getAffiliation() {
        return affiliation;
    }

    /**
     * The method that registers a session
     * @param session to add
     */
    public boolean registerSession(Session session){
        return this.sessions.add(session);
    }

    /**
     * The method that returns a list of sessions associated with this user
     * @return a list of sessions
     */
    public ArrayList<Session> listSessions(){
        return this.sessions;
    }

    /**
     * Method that performs sign in of user
     * @param password in plaintext
     * @return a RequestResult that indicates success or failure of sign in
     */
    public RequestResult signIn(String password) {
        if (password == null) return new RequestResult(false, false, "Password cannot be empty");
        if (username != null && !username.startsWith("p3") && affiliation.equals("student"))
            return new RequestResult(false, false, "You are not allowed to login");

        String hashedPassword = Password.hash(password);
        if(hashedPassword.equals(getPasswordHash())){
            return new RequestResult(true, !affiliation.equals("student"), "Success");
        }
        return new RequestResult(false, false,"Wrong password");
    }
}
