package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.people.Administrator;
import gr.aueb.se.labadministration.domain.people.User;

import java.util.ArrayList;

/**
 * The interface the communicates with databases for the users
 */
public interface UserDAO {

    /**
     * The method that saves users to the database
     * @param user
     */
    void save(User user);

    /**
     * The method that removes users from a database
     * @param user
     */
    void delete(User user);

    /**
     * The method that finds users by their username
     * @param username
     * @return user or null
     */
    User find(String username);

    /**
     * The method that returns all users
     * @return
     */
    ArrayList<User> listUsers();

    /**
     * The method that returns the data from which terminals have been used by the user
     * @param user
     * @return user or null
     */
    ArrayList<Session> listAllSessions(User user);

    /**
     * The method that returns all the administrators
     * @return all users that are admins
     */
    ArrayList<Administrator> listAllAdministrators();
}
