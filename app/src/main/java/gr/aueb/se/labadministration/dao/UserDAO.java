package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.lab.Session;
import gr.aueb.se.labadministration.people.Administrator;
import gr.aueb.se.labadministration.people.User;

import java.util.ArrayList;

public interface UserDAO {

    void save(User user);

    void delete(User user);

    User find(String username);

    ArrayList<Session> listAllSessions(User user);

    ArrayList<Administrator> listAllAdministrators();
}
