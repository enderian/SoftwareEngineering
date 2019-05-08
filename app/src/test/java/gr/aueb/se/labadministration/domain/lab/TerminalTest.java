package gr.aueb.se.labadministration.domain.lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;

public class TerminalTest {

    private Terminal terminal;
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

        this.terminal = new TerminalBuilder()
                .setConfiguration(configuration)
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setHostname("1")
                .setName("1")
                .setPositionX(1)
                .setPositionY(1)
                .createTerminal();

        this.terminal.setStatus(Terminal.TerminalStatus.AVAILABLE);
    }

    @Test
    public void getHostname() {
        Assert.assertEquals("1", this.terminal.getHostname());
    }

    @Test
    public void setHostname() {
        this.terminal.setHostname("NewHN");
    }

    @Test
    public void getIpAddress() throws UnknownHostException {
        Assert.assertEquals(InetAddress.getByName("127.0.0.1"), this.terminal.getIpAddress());
    }

    @Test
    public void setIpAddress() throws UnknownHostException {
        this.terminal.setIpAddress(InetAddress.getByName("192.168.1.2"));
    }

    @Test
    public void getStatus() {
        Assert.assertEquals(Terminal.TerminalStatus.AVAILABLE, this.terminal.getStatus());
    }

    @Test
    public void getPositionX() {
        Assert.assertEquals(1, this.terminal.getPositionX());
    }

    @Test
    public void setPositionX() {
        this.terminal.setPositionX(2);
    }

    @Test
    public void getPositionY() {
        Assert.assertEquals(1, this.terminal.getPositionY());
    }

    @Test
    public void setPositionY() {
        this.terminal.setPositionY(2);
    }

    @Test
    public void getConfiguration() {
        Assert.assertNotNull(this.terminal.getConfiguration());
    }

    @Test
    public void setConfiguration() {
        this.terminal.setConfiguration(this.configuration);
    }

    @Test
    public void getName() {
        Assert.assertEquals("1", this.terminal.getName());
    }

    @Test
    public void setName() {
        this.terminal.setName("newName");
    }

    @Test
    public void setStatus() {
        this.terminal.setStatus(Terminal.TerminalStatus.IN_USE);
    }

}