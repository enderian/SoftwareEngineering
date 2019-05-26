package gr.aueb.se.labadministration.services;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;

import static org.junit.Assert.*;

public class ConfigurationServiceTest {

    private static ConfigurationService configurationService;

    @BeforeClass
    public static void setup(){
        configurationService = new ConfigurationServiceStub();
    }

    @Test
    public void listAllConfigs(){
        List<TerminalConfiguration> configurationList = configurationService.listAllConfigs();

        Assert.assertNull(configurationList);
    }

    @Test
    public void saveConfig(){
        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setName("T2")
                .setOperatingSystem("OS")
                .setProcessor("i")
                .setstorageCapacity(2014)
                .setTotalMemory(1024)
                .createTerminalConfiguration();
        configurationService.saveConfiguration(configuration);

        TerminalConfiguration test = ((ConfigurationServiceStub) configurationService).getTests();

        Assert.assertEquals(test, configuration);
    }

    @Test
    public void searchTest(){
        TerminalConfiguration test = configurationService.findConfig(
                new TerminalConfigurationBuilder()
                        .setName("T")
                        .setOperatingSystem("OS")
                        .setProcessor("i")
                        .setstorageCapacity(2014)
                        .setTotalMemory(1024)
                        .createTerminalConfiguration()
        );

        Assert.assertEquals(test.getName(), "T");
    }
}