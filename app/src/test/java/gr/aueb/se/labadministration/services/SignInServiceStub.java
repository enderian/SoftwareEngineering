package gr.aueb.se.labadministration.services;

import java.util.ArrayList;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.people.Administrator;
import gr.aueb.se.labadministration.domain.people.User;

/**
 * Sign In User DAO Stub
 */
public class SignInServiceStub extends SignInService {

    @Override
    public UserDAO getUserDAO() {
        return new UserDAO() {
            @Override
            public void save(User user) {

            }

            @Override
            public void delete(User user) {

            }

            @Override
            public User find(String username) {
                if (username == null) return null;
                switch (username) {
                    case "d2111111": return new User("d2111111", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student");
                    case "p3160026": return new User("p3160026", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student");
                    case "teacher": return new User("teacher", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "teacher");
                }
                return null;
            }

            @Override
            public ArrayList<User> listUsers() {
                return null;
            }

            @Override
            public ArrayList<Session> listAllSessions(User user) {
                return null;
            }

            @Override
            public ArrayList<Administrator> listAllAdministrators() {
                return null;
            }
        };
    }
}
