package gr.aueb.se.labadministration.dao.inmemory;

import com.google.common.collect.Lists;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.people.Administrator;
import gr.aueb.se.labadministration.domain.people.User;

import java.util.ArrayList;

public class UserDAOMemory implements UserDAO {

    private ArrayList<User> users = Lists.newArrayList(
            new Administrator("p3150133", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8" /*password*/, "student"),
            new User("p3160026", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8" /*password*/, "student"),
            new User("p3170000", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8" /*password*/, "student"),
            new User("iamateacher", "BCB1496530F97CAF5AD2638187D19D498B957B19EF2F81DDE25C22E99FBBAF5F" /*aueb*/, "teacher"),
            new User("d2111111", "E66E38BD8C19B24CF0EE388183162EA7CD63FF7912DBB22B5E83286B4446" /*user123*/, "student")
    );

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public User find(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    @Override
    public ArrayList<Session> listAllSessions(User user) {
        for (User u : users) {
            if (u.equals(user)) return u.listSessions();
        }
        return null;
    }

    @Override
    public ArrayList<Administrator> listAllAdministrators() {
        ArrayList<Administrator> administrators = new ArrayList<>();
        for (User u : users) {
            if (u instanceof Administrator) administrators.add((Administrator) u);
        }
        return administrators;
    }
}
