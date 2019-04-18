package gr.aueb.se.labadministration.lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import gr.aueb.se.labadministration.builder.TerminalBuilder;
import gr.aueb.se.labadministration.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.people.User;

public class SessionTest {

    private Terminal terminal;
    private User user;
    private TerminalConfiguration configuration;

    @Before
    public void initiate() throws UnknownHostException {

        this.configuration = new TerminalConfigurationBuilder()
                .setName("T")
                .setOperatingSystem("OS")
                .setProcessor("i")
                .setstorageCapacity(2014)
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

    }

    @Test
    public void addSession(){

        Session session = new Session(terminal, user, Session.SessionStatus.FINISHED, new Date(), new Date());

        boolean result = session.updateSessions();

        Assert.assertTrue(result);

    }

}