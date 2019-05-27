package gr.aueb.se.labadministration.domain.builder;

import org.junit.Assert;
import org.junit.Test;

import java.net.UnknownHostException;

import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;


public class TerminalConfigurationBuilderTest {

    @Test
    public void createTerminalConfigurationWithGFX(){

        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setName("1")
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setGraphicsCard("GT-710")
                .setstorageCapacity(1024)
                .setTotalMemory(8192)
                .createTerminalConfiguration();

        Assert.assertNotNull(configuration);

    }

    /**
     * Test that creates Terminal Configuration successfully
     * @throws UnknownHostException
     */
    @Test
    public void createTerminalConfigurationWithNoGFX(){

        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setName("1")
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setstorageCapacity(1024)
                .setTotalMemory(8192)
                .createTerminalConfiguration();

        Assert.assertNotNull(configuration);

    }

    /**
     * Test that does not create Terminal Configuration successfully
     * @throws UnknownHostException
     */
    @Test
    public void createTerminalConfigurationFailDueToNoName(){

        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setGraphicsCard("GT-710")
                .setstorageCapacity(1024)
                .setTotalMemory(8192)
                .createTerminalConfiguration();

        Assert.assertNull(configuration);

    }

    /**
     * Test that does not create Terminal Configuration successfully
     * @throws UnknownHostException
     */
    @Test
    public void createTerminalConfigurationFailDueToNoOS(){

        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setName("1")
                .setProcessor("i7")
                .setGraphicsCard("GT-710")
                .setstorageCapacity(1024)
                .setTotalMemory(8192)
                .createTerminalConfiguration();

        Assert.assertNull(configuration);

    }

    /**
     * Test that does not create Terminal Configuration successfully
     * @throws UnknownHostException
     */
    @Test
    public void createTerminalConfigurationFailDueToNoStorage(){

        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setName("1")
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setGraphicsCard("GT-710")
                .setTotalMemory(8192)
                .createTerminalConfiguration();

        Assert.assertNull(configuration);

    }

    /**
     * Test that does not create Terminal Configuration successfully
     * @throws UnknownHostException
     */
    @Test
    public void createTerminalConfigurationFailDueToNoMemory(){

        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setName("1")
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setGraphicsCard("GT-710")
                .setstorageCapacity(1024)
                .createTerminalConfiguration();

        Assert.assertNull(configuration);

    }
}