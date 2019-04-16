package gr.aueb.se.labadministration.people;

public class User {

    private String username;
    private String passwordHash;
    private String affiliation;

    public User(String username, String passwordHash, String affiliation) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.affiliation = affiliation;
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

}
