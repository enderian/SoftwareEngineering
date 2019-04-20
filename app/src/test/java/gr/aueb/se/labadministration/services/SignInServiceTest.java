package gr.aueb.se.labadministration.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.dao.inmemory.UserDAOMemory;
import gr.aueb.se.labadministration.domain.people.User;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignInServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private SignInService signIn = new SignInServiceImpl();

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
        when(userDAO.find("p3160026")).thenReturn(new User(
                "p3160026",
                "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8",
                "student"));
        when(userDAO.find("d2111111")).thenReturn(new User(
                "d2111111",
                "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8",
                "student"));
        when(userDAO.find("iamateacher")).thenReturn(new User(
                "iamateacher",
                "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8",
                "teacher"));
    }

    @Test
    public void successfulSignIn() throws NoSuchAlgorithmException {
        SignInResult signInResult = this.signIn.execute("p3160026", "password");

        Assert.assertTrue(signInResult.isSuccessful());
        Assert.assertEquals("Success", signInResult.getReasonOfFailure());
    }

    @Test
    public void testEmptyPassword() throws NoSuchAlgorithmException {
        SignInResult signInResult = this.signIn.execute("p3160026", null);

        Assert.assertFalse(signInResult.isSuccessful());
        Assert.assertEquals("Unknown parameters", signInResult.getReasonOfFailure());
    }

    @Test
    public void registerUser() throws NoSuchAlgorithmException {
        SignInResult signInResult = this.signIn.execute("p3160026", "password");

        Assert.assertTrue(signInResult.isSuccessful());
        Assert.assertEquals("Success", signInResult.getReasonOfFailure());
    }

    @Test
    public void forbiddenUser() throws NoSuchAlgorithmException {
        SignInResult signInResult = this.signIn.execute("d2111111", "password");

        Assert.assertFalse(signInResult.isSuccessful());
        Assert.assertEquals("You are not allowed to login", signInResult.getReasonOfFailure());
    }

    @Test
    public void nonExistentUser() throws NoSuchAlgorithmException {
        SignInResult signInResult = this.signIn.execute("p3160000", "password");

        Assert.assertFalse(signInResult.isSuccessful());
        Assert.assertEquals("User not found", signInResult.getReasonOfFailure());
    }

    @Test
    public void wrongPassword() throws NoSuchAlgorithmException {
        SignInResult signInResult = this.signIn.execute("p3160026", "passwor");

        Assert.assertFalse(signInResult.isSuccessful());
        Assert.assertEquals("Wrong password", signInResult.getReasonOfFailure());
    }

    @Test
    public void brokenParameters() throws NoSuchAlgorithmException {
        SignInResult signInResult = this.signIn.execute(null, null);

        Assert.assertFalse(signInResult.isSuccessful());
        Assert.assertEquals("Unknown parameters", signInResult.getReasonOfFailure());
    }

    @Test
    public void notStudentLogin() throws NoSuchAlgorithmException {
        SignInResult signInResult = this.signIn.execute("iamateacher", "password");

        Assert.assertTrue(signInResult.isSuccessful());
        Assert.assertEquals("Success", signInResult.getReasonOfFailure());
    }
}