package gr.aueb.se.labadministration.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.people.User;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignInTest {

    private User user;
    private UserDAO userDAO;
    private SignIn signIn;

    @Before
    public void initialize(){

        this.user = new User("p3160026", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student");
        this.userDAO = new UserDAOMemory();
        this.signIn = new SignIn();

    }

    @Test
    public void successfulSignIn() throws NoSuchAlgorithmException {

        this.userDAO.save(this.user);

        RequestResult requestResult = this.signIn.signInRequest("p3160026", "password", "student");

        Assert.assertTrue(requestResult.isSuccessful());
        Assert.assertEquals("Success", requestResult.getReasonOfFailure());

    }

    @Test
    public void testEmptyPassword() throws NoSuchAlgorithmException {

        this.userDAO.save(this.user);

        RequestResult requestResult = this.signIn.signInRequest("p3160026", null, "student");

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("Password cannot be empty", requestResult.getReasonOfFailure());
    }

    @Test
    public void registerUser() throws NoSuchAlgorithmException {

        RequestResult requestResult = this.signIn.signInRequest("p3160026", "password", "student");

        Assert.assertTrue(requestResult.isSuccessful());
        Assert.assertEquals("Success", requestResult.getReasonOfFailure());
    }

    @Test
    public void forbiddenUser() throws NoSuchAlgorithmException {

        RequestResult requestResult = this.signIn.signInRequest("d2111111", "password", "student");

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("You are not allowed to login", requestResult.getReasonOfFailure());
    }

    @Test
    public void wrongPassword() throws NoSuchAlgorithmException {

        this.userDAO.save(this.user);

        RequestResult requestResult = this.signIn.signInRequest("p3160026", "passwor", "student");

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("Wrong password", requestResult.getReasonOfFailure());
    }

    @Test
    public void brokenParameters() throws NoSuchAlgorithmException {

        RequestResult requestResult = this.signIn.signInRequest(null, null, null);

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("Unknown parameters", requestResult.getReasonOfFailure());
    }

    @Test
    public void notStudentLogin() throws NoSuchAlgorithmException {

        RequestResult requestResult = this.signIn.signInRequest("username", "password", "teacher");

        Assert.assertTrue(requestResult.isSuccessful());
        Assert.assertEquals("Success", requestResult.getReasonOfFailure());
    }
}