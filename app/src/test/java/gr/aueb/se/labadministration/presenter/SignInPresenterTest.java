package gr.aueb.se.labadministration.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.people.User;
import gr.aueb.se.labadministration.services.SignIn;
import gr.aueb.se.labadministration.stubs.SignInFragmentStub;

import static org.junit.Assert.*;

public class SignInPresenterTest {

    private SignInPresenter presenter;
    private SignInFragmentStub stub;
    private SignIn signIn;
    private UserDAO userDAO;
    private User user;

    @Before
    public void initialize(){
        stub = new SignInFragmentStub();
        signIn = new SignIn();
        presenter = new SignInPresenter(stub);
        userDAO = new UserDAOMemory();
        user = new User("p3160026", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student");
    }

    @Test
    public void fragmentOpened(){
        presenter.start();
        Assert.assertTrue(stub.isOpened());
    }

    @Test
    public void successfulSignIn(){
        this.userDAO.save(user);

        presenter.start();
        stub.performSignIn("p3160026", "password");

        stub.hide();

        Assert.assertTrue(stub.getSuccess());
    }

    @Test
    public void failedSignIn(){
        this.userDAO.save(user);

        presenter.start();
        stub.performSignIn("p3160026", "passwor");

        Assert.assertFalse(stub.getSuccess());
        Assert.assertEquals("Failed to login", stub.getFail());
        Assert.assertNotNull(stub.getReasonOfFailure());
    }

}