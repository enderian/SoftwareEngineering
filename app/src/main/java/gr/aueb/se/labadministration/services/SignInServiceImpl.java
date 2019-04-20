package gr.aueb.se.labadministration.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.domain.people.User;

public class SignInServiceImpl implements SignInService {

    @Inject
    private UserDAO userDAO;

    @Override
    public SignInResult execute(String username, String password) throws NoSuchAlgorithmException {

        if (username == null || password == null)
            return new SignInResult(false, "Unknown parameters");

        User user = userDAO.find(username);

        if (user == null)
            return new SignInResult(false, "User not found.");
        if (!username.startsWith("p3") && user.getAffiliation().equals("student"))
            return new SignInResult(false, "You are not allowed to login");

        String hashedPassword = hashPassword(password);
        if(hashedPassword.equals(user.getPasswordHash())){
            return new SignInResult(true, "Success");
        }
        return new SignInResult(false, "Wrong password");
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
