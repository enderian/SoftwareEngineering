package gr.aueb.se.labadministration.services;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignIn {

    public RequestResult signInRequest(String username, String password) throws NoSuchAlgorithmException {
        UserDAO userDAO = new UserDAOMemory();
        userDAO.save(new User("d2111111", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student"));
        userDAO.save(new User("p3160026", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student"));
        userDAO.save(new User("username", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "teacher"));
        User user = userDAO.find(username);
        if (user == null) return new RequestResult(false, "User not found");

        return user.signIn(password);
    }


}
