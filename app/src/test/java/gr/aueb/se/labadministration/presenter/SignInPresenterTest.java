package gr.aueb.se.labadministration.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.services.SignIn;
import gr.aueb.se.labadministration.stubs.SignInActivityStub;

public class SignInPresenterTest {

    private SignInPresenter presenter;
    private SignInActivityStub stub;
    private SignIn signIn;
    private UserDAO userDAO;
    private User user;

    @Before
    public void initialize(){
        stub = new SignInActivityStub();
        signIn = new SignIn();
        presenter = new SignInPresenter(stub);
        userDAO = new UserDAOMemory();
        user = new User("p3160026", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student");
    }

    @Test
    public void successfulSignIn(){
        this.userDAO.save(user);

        stub.setPassword("password");
        stub.setUsername("p3160026");
        presenter.start();
        presenter.performSignIn();

        Assert.assertTrue(stub.isSuccess());
    }

    @Test
    public void failedSignIn(){
        this.userDAO.save(user);

        stub.setPassword("passord");
        stub.setUsername("p3160026");
        presenter.start();
        presenter.performSignIn();

        Assert.assertFalse(stub.isSuccess());
    }

}