package gr.aueb.se.labadministration.services;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignIn {

    public RequestResult signInRequest(String username, String password) throws NoSuchAlgorithmException {
        UserDAO userDAO = new UserDAOMemory();

        User user = userDAO.find(username);
        if (user == null) return new RequestResult(false, "User not found");

        return user.signIn(password);
    }


}
