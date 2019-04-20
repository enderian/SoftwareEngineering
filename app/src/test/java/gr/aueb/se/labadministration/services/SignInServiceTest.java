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
    public void successfulSignIn() {
        SignInResult signInResult = this.signIn.execute("p3160026", "password");

        Assert.assertTrue(signInResult.isSuccessful());
        Assert.assertEquals("Success", signInResult.getReasonOfFailure());
    }

    @Test
    public void testEmptyPassword() {
        SignInResult signInResult = this.signIn.execute("p3160026", null);

        Assert.assertFalse(signInResult.isSuccessful());
        Assert.assertEquals("Unknown parameters", signInResult.getReasonOfFailure());
    }

    @Test
    public void registerUser() {
        SignInResult signInResult = this.signIn.execute("p3160026", "password");

        Assert.assertTrue(signInResult.isSuccessful());
        Assert.assertEquals("Success", signInResult.getReasonOfFailure());
    }

    @Test
    public void forbiddenUser() {
        SignInResult signInResult = this.signIn.execute("d2111111", "password");

        Assert.assertFalse(signInResult.isSuccessful());
        Assert.assertEquals("You are not allowed to login", signInResult.getReasonOfFailure());
    }

    @Test
    public void nonExistentUser() {
        SignInResult signInResult = this.signIn.execute("p3160000", "password");

        Assert.assertFalse(signInResult.isSuccessful());
        Assert.assertEquals("User not found.", signInResult.getReasonOfFailure());
    }

    @Test
    public void wrongPassword() {
        SignInResult signInResult = this.signIn.execute("p3160026", "passwor");

        Assert.assertFalse(signInResult.isSuccessful());
        Assert.assertEquals("Wrong password", signInResult.getReasonOfFailure());
    }

    @Test
    public void brokenParameters() {
        SignInResult signInResult = this.signIn.execute(null, null);

        Assert.assertFalse(signInResult.isSuccessful());
        Assert.assertEquals("Unknown parameters", signInResult.getReasonOfFailure());
    }

    @Test
    public void notStudentLogin() {
        SignInResult signInResult = this.signIn.execute("iamateacher", "password");

        Assert.assertTrue(signInResult.isSuccessful());
        Assert.assertEquals("Success", signInResult.getReasonOfFailure());
    }

    @Test
    public void hashPassword() throws NoSuchAlgorithmException {
        Assert.assertEquals(SignInServiceImpl.hashPassword("password"),
                "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8");
        Assert.assertEquals(SignInServiceImpl.hashPassword("user123"),
                "E66E38BD8C19B24CF0EE388183162EA7CD63FF7912DBB22B5E83286B4446");
        Assert.assertEquals(SignInServiceImpl.hashPassword("aueb"),
                "BCB1496530F97CAF5AD2638187D19D498B957B19EF2F81DDE25C22E99FBBAF5F");
    }
}