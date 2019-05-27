package gr.aueb.se.labadministration.services;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignInServiceTest {

    private static SignInService signIn;

    @BeforeClass
    public static void setup(){
        signIn = new SignInServiceStub();
    }

    /**
     * Test that performs successful sign in
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void successfulSignIn() throws NoSuchAlgorithmException {
        RequestResult requestResult = signIn.signInRequest("p3160026", "password");

        Assert.assertTrue(requestResult.isSuccessful());
        Assert.assertEquals("Success", requestResult.getReasonOfFailure());
    }

    /**
     * Test that does not perform successful sign in due to empty password
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testEmptyPassword() throws NoSuchAlgorithmException {
        RequestResult requestResult = signIn.signInRequest("p3160026", null);

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("Password cannot be empty", requestResult.getReasonOfFailure());
    }

    /**
     * Test that registers a user
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void registerUser() throws NoSuchAlgorithmException {

        RequestResult requestResult = signIn.signInRequest("p3160026", "password");

        Assert.assertTrue(requestResult.isSuccessful());
        Assert.assertEquals("Success", requestResult.getReasonOfFailure());
    }

    /**
     * Test that does not perform successful sign in due to ban
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void forbiddenUser() throws NoSuchAlgorithmException {

        RequestResult requestResult = signIn.signInRequest("d2111111", "password");

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("You are not allowed to login", requestResult.getReasonOfFailure());
    }

    /**
     * Test that does not perform successful sign in due to wrong password
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void wrongPassword() throws NoSuchAlgorithmException {

        RequestResult requestResult = signIn.signInRequest("p3160026", "passwor");

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("Wrong password", requestResult.getReasonOfFailure());
    }

    /**
     * Test that does not perform successful sign in due to broken parameters
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void brokenParameters() throws NoSuchAlgorithmException {

        RequestResult requestResult = signIn.signInRequest(null, null);

        Assert.assertFalse(requestResult.isSuccessful());
        Assert.assertEquals("User not found", requestResult.getReasonOfFailure());
    }

    /**
     * Test that performs successful sign in due to the fact that he is a non student
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void notStudentLogin() throws NoSuchAlgorithmException {

        RequestResult requestResult = signIn.signInRequest("teacher", "password");

        Assert.assertTrue(requestResult.isSuccessful());
        Assert.assertEquals("Success", requestResult.getReasonOfFailure());
    }

    @Test
    public void getUserDAO() {
        UserDAO dao = signIn.getUserDAO();

        Assert.assertNotNull(dao);
    }
}