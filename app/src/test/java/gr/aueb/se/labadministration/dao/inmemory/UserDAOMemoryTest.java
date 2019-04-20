package gr.aueb.se.labadministration.dao.inmemory;

import org.junit.Assert;
import org.junit.Test;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.dao.inmemory.UserDAOMemory;
import gr.aueb.se.labadministration.domain.people.User;

public class UserDAOMemoryTest {

    private UserDAO userDAO = new UserDAOMemory();

    @Test
    public void findUsers() {
        Assert.assertNotNull(userDAO.find("p3150133"));
        Assert.assertNotNull(userDAO.find("p3160026"));
        Assert.assertNotNull(userDAO.find("iamateacher"));

        Assert.assertNull(userDAO.find("idonotexist"));
    }

    @Test
    public void deleteUser() {
        userDAO.delete(userDAO.find("d2111111"));

        Assert.assertNull(userDAO.find("d2111111"));
    }

    @Test
    public void saveUser() {
        userDAO.save(new User("p3180000", "?", "student"));

        Assert.assertNotNull(userDAO.find("p3180000"));
    }

}
