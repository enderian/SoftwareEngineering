package gr.aueb.se.labadministration.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.people.User;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignIn {

    public RequestResult signInRequest(String username, String password, String affiliation) throws NoSuchAlgorithmException {

        UserDAO userDAO = new UserDAOMemory();

        User user = userDAO.find(username);

        if (user != null && password == null) return new RequestResult(false, "Password cannot be empty");

        if (username != null && (username.startsWith("p3") || !affiliation.equals("student")) && user == null){
            String hashed = hashPassword(password);
            user = new User(username, hashed, affiliation);
            userDAO.save(user);
        }

        if (username != null && !username.startsWith("p3") && affiliation.equals("student") && user == null)
            return new RequestResult(false, "You are not allowed to login");

        if (user != null){
            String hashedPassword = hashPassword(password);
            if(hashedPassword.equals(user.getPasswordHash())){
                return new RequestResult(true, "Success");
            }
            return new RequestResult(false, "Wrong password");
        }

        return new RequestResult(false, "Unknown parameters");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] messageDigest = md.digest();
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < messageDigest.length; i++)
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        return hexString.toString().toUpperCase();
    }

}
