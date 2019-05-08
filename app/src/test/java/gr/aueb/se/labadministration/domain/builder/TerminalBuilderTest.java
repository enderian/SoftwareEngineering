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
    private SoftwarePackage softwarePackage;

    @Before
    public void setConfifuration(){
        this.softwarePackage = new SoftwarePackage("AS", "1", "2");
        this.terminalConfiguration = new TerminalConfigurationBuilder()
                .setName("testConf")
                .setGraphicsCard("GT-510")
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setstorageCapacity(1024)
                .setTotalMemory(8192)
                .createTerminalConfiguration();
        this.terminalConfiguration.addSoftwarePackage(softwarePackage);
    }

    @Test
    public void createTerminal() throws UnknownHostException {

        Terminal terminal = new TerminalBuilder()
                .setConfiguration(terminalConfiguration)
                .setHostname("Lab-TestName")
                .setName("TestName")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setPositionX(1)
                .setPositionY(0)
                .createTerminal();

        Assert.assertNotNull(terminal);

    }

    @Test
    public void createTerminalFailDueToNoConf() throws UnknownHostException {

        Terminal terminal = new TerminalBuilder()
                .setHostname("Lab-TestName")
                .setName("TestName")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setPositionX(1)
                .setPositionY(0)
                .createTerminal();

        Assert.assertNull(terminal);

    }

    @Test
    public void createTerminalFailDueToNoHostname() throws UnknownHostException {

        Terminal terminal = new TerminalBuilder()
                .setConfiguration(terminalConfiguration)
                .setName("TestName")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setPositionX(1)
                .setPositionY(0)
                .createTerminal();

        Assert.assertNull(terminal);

    }

    @Test
    public void createTerminalFailDueToNoName() throws UnknownHostException {

        Terminal terminal = new TerminalBuilder()
                .setConfiguration(terminalConfiguration)
                .setHostname("Lab-TestName")
                .setIpAddress(InetAddress.getByName("127.0.0.1"))
                .setPositionX(1)
                .setPositionY(0)
                .createTerminal();

        Assert.assertNull(terminal);

    }

    @Test
    public void createTerminalFailDueToNoIP() {

        Terminal terminal = new TerminalBuilder()
                .setConfiguration(terminalConfiguration)
                .setHostname("Lab-TestName")
                .setName("TestName")
                .setPositionX(1)
                .setPositionY(0)
                .createTerminal();

        Assert.assertNull(terminal);

    }
}