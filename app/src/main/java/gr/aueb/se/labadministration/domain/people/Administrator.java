package gr.aueb.se.labadministration.domain.people;

import java.io.Serializable;

public class Administrator extends User implements Serializable {

    public Administrator(String username, String passwordHash, String affiliation) {
        super(username, passwordHash, affiliation);
    }

}
