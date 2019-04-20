package gr.aueb.se.labadministration.domain.builder;

import com.google.common.collect.Lists;

import org.junit.Assert;
import org.junit.Test;

import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;


public class TerminalConfigurationBuilderTest {

    @Test
    public void createTerminalConfigurationWithGFX() {

        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setName("1")
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setGraphicsCard("GT-710")
                .setStorageCapacity(1024)
                .setTotalMemory(8192)
                .createTerminalConfiguration();

        Assert.assertNotNull(configuration);

    }

    @Test
    public void createTerminalConfigurationWithNoGFX() {

        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setName("1")
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setStorageCapacity(1024)
                .setTotalMemory(8192)
                .createTerminalConfiguration();

        Assert.assertNotNull(configuration);

    }

    @Test(expected = IllegalArgumentException.class)
    public void createTerminalConfigurationFailDueToNoName() {
        new TerminalConfigurationBuilder()
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setGraphicsCard("GT-710")
                .setStorageCapacity(1024)
                .setTotalMemory(8192)
                .createTerminalConfiguration();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTerminalConfigurationFailDueToNoOS() {
        new TerminalConfigurationBuilder()
                .setName("1")
                .setProcessor("i7")
                .setGraphicsCard("GT-710")
                .setStorageCapacity(1024)
                .setTotalMemory(8192)
                .createTerminalConfiguration();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTerminalConfigurationFailDueToNoStorage() {
        new TerminalConfigurationBuilder()
                .setName("1")
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setGraphicsCard("GT-710")
                .setTotalMemory(8192)
                .createTerminalConfiguration();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTerminalConfigurationFailDueToNoMemory() {
        new TerminalConfigurationBuilder()
                .setName("1")
                .setOperatingSystem("Windows")
                .setProcessor("i7")
                .setGraphicsCard("GT-710")
                .setStorageCapacity(1024)
                .setSoftwarePackages(Lists.newArrayList(
                        new SoftwarePackage("1", "2", "3")
                ))
                .createTerminalConfiguration();
    }
}