package gr.aueb.se.labadministration.domain.lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.people.User;

public class SessionTest {

    private Terminal terminal;
    private User user;
    private TerminalConfiguration configuration;
    private Session session;

    @Before
    public void initiate() throws UnknownHostException {

        this.configuration = new TerminalConfigurationBuilder()
                .setName("T")
                .setOperatingSystem("OS")
                .setProcessor("i")
                .setStorageCapacity(2014)
                .setTotalMemory(1024)
                .createTerminalConfiguration();

        this.user = new User("p31xxxx", "1", "1");
        this.terminal = new TerminalBuilder()
                .setConfiguration(configuration)
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setHostname("1")
                .setName("1")
                .setPositionX(1)
                .setPositionY(1)
                .createTerminal();

        this.session = new Session(terminal, user, Session.SessionStatus.FINISHED, new Date(), new Date());

    }

    @Test
    public void addSession(){

        boolean result = session.updateSessions();

        Assert.assertTrue(result);

    }

    @Test
    public void getTerminal() {
        Assert.assertEquals(terminal, this.session.getTerminal());
    }

    @Test
    public void getUser() {
        Assert.assertEquals(user, this.session.getUser());
    }

    @Test
    public void setUser() {
        this.session.setUser(new User("1", "2", "3"));
    }

    @Test
    public void getStatus() {
        Assert.assertEquals(Session.SessionStatus.FINISHED, this.session.getStatus());
    }

    @Test
    public void getStartTime() {
        Assert.assertNotNull(this.session.getStartTime());
    }

    @Test
    public void getEndTime() {
        Assert.assertNotNull(this.session.getEndTime());
    }
}