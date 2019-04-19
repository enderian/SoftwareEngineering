package gr.aueb.se.labadministration.people;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void initiate(){

        this.user = new User("p31xxxx", "1", "2");

    }

    @Test
    public void getUsername() {
        Assert.assertEquals("p31xxxx", this.user.getUsername());
    }

    @Test
    public void getPasswordHash() {
        Assert.assertEquals("1", this.user.getPasswordHash());
    }

    @Test
    public void getAffiliation() {
        Assert.assertEquals("2", this.user.getAffiliation());
    }

    @Test
    public void listSessions() {
        Assert.assertNotNull(this.user.listSessions());
    }
}