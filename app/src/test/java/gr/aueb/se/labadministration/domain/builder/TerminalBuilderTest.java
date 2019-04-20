package gr.aueb.se.labadministration.domain.builder;

import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TerminalBuilderTest {

    private TerminalConfiguration terminalConfiguration;

    @Before
    public void setConfifuration(){
        SoftwarePackage softwarePackage = new SoftwarePackage("AS", "1", "2");
        this.terminalConfiguration = new TerminalConfigurationBuilder()
                .setName("testConf")
                .setGraphicsCard("GT-510")
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setStorageCapacity(1024)
                .setTotalMemory(8192)
                .createTerminalConfiguration();
        this.terminalConfiguration.addSoftwarePackage(softwarePackage);
    }

    @Test
    public void createTerminal() throws UnknownHostException {
        new TerminalBuilder()
                .setConfiguration(terminalConfiguration)
                .setHostname("Lab-TestName")
                .setName("TestName")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setPositionX(1)
                .setPositionY(0)
                .createTerminal();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTerminalFailDueToNoConf() throws UnknownHostException {
        new TerminalBuilder()
                .setHostname("Lab-TestName")
                .setName("TestName")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setPositionX(1)
                .setPositionY(0)
                .createTerminal();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTerminalFailDueToNoHostname() throws UnknownHostException {
        new TerminalBuilder()
                .setConfiguration(terminalConfiguration)
                .setName("TestName")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setPositionX(1)
                .setPositionY(0)
                .createTerminal();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTerminalFailDueToNoName() throws UnknownHostException {
        new TerminalBuilder()
                .setConfiguration(terminalConfiguration)
                .setHostname("Lab-TestName")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setPositionX(1)
                .setPositionY(0)
                .createTerminal();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTerminalFailDueToNoIP() {
        new TerminalBuilder()
                .setConfiguration(terminalConfiguration)
                .setHostname("Lab-TestName")
                .setName("TestName")
                .setPositionX(1)
                .setPositionY(0)
                .createTerminal();
    }
}