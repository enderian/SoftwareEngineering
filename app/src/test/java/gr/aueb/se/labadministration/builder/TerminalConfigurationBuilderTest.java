package gr.aueb.se.labadministration.builder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gr.aueb.se.labadministration.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.configurations.TerminalConfiguration;


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