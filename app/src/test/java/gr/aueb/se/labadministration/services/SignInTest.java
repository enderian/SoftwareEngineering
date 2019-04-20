package gr.aueb.se.labadministration.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.people.User;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignInTest {

    private static UserDAO userDAO;
    private static SignIn signIn;

    @BeforeClass
    public static void setup(){
        userDAO = new UserDAOMemory();
        userDAO.save(new User("d2111111", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student"));
        userDAO.save(new User("p3160026", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student"));
        userDAO.save(new User("username", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "teacher"));

        signIn = new SignIn();
    }

    @Test
    public void successfulSignIn() throws NoSuchAlgorithmException {
        RequestResult requestResult = signIn.signInRequest("p3160026", "password");

        Assert.assertTrue(requestResult.isSuccessful());
        Assert.assertEquals("Success", requestResult.getReasonOfFailure());

    }

    @Test
    public void testEmptyPassword() throws NoSuchAlgorithmException {
        RequestResult requestResult = signIn.signInRequest("p3160026", null);

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("Password cannot be empty", requestResult.getReasonOfFailure());
    }

    @Test
    public void registerUser() throws NoSuchAlgorithmException {

        RequestResult requestResult = signIn.signInRequest("p3160026", "password");

        Assert.assertTrue(requestResult.isSuccessful());
        Assert.assertEquals("Success", requestResult.getReasonOfFailure());
    }

    @Test
    public void forbiddenUser() throws NoSuchAlgorithmException {

        RequestResult requestResult = signIn.signInRequest("d2111111", "password");

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("You are not allowed to login", requestResult.getReasonOfFailure());
    }

    @Test
    public void wrongPassword() throws NoSuchAlgorithmException {

        RequestResult requestResult = signIn.signInRequest("p3160026", "passwor");

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("Wrong password", requestResult.getReasonOfFailure());
    }

    @Test
    public void brokenParameters() throws NoSuchAlgorithmException {

        RequestResult requestResult = signIn.signInRequest(null, null);

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("User not found", requestResult.getReasonOfFailure());
    }

    @Test
    public void notStudentLogin() throws NoSuchAlgorithmException {

        RequestResult requestResult = signIn.signInRequest("username", "password");

        Assert.assertTrue(requestResult.isSuccessful());
        Assert.assertEquals("Success", requestResult.getReasonOfFailure());
    }
}