package gr.aueb.se.labadministration.domain.people;

import java.io.Serializable;

/**
 * The class that defines an administrator
 */
public class Administrator extends User implements Serializable {

    public Administrator(String username, String passwordHash, String affiliation) {
        super(username, passwordHash, affiliation);
    }

}
