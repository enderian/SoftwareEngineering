package gr.aueb.se.labadministration.domain.people;

public class Administrator extends User {

    public Administrator(String username, String passwordHash, String affiliation) {
        super(username, passwordHash, affiliation);
    }

}
