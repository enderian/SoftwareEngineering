package gr.aueb.se.labadministration.domain.people;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Setters & Getters Tests
 */
public class UserTest {

    private User user;

    @Before
    public void initiate(){

        this.user = new User("p31xxxx", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "2");

    }

    @Test
    public void getUsername() {
        Assert.assertEquals("p31xxxx", this.user.getUsername());
    }

    @Test
    public void getPasswordHash() {
        Assert.assertEquals("5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", this.user.getPasswordHash());
    }

    @Test
    public void getAffiliation() {
        Assert.assertEquals("2", this.user.getAffiliation());
    }

    @Test
    public void listSessions() {
        Assert.assertNotNull(this.user.listSessions());
    }

    /**
     * Test that performs successful sign in
     */
    @Test
    public void signIn() {
        Assert.assertTrue(this.user.signIn("password").isSuccessful());
    }
}