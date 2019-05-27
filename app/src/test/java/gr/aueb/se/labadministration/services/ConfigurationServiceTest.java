package gr.aueb.se.labadministration.services;

import android.os.IBinder;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;

public class ConfigurationServiceTest {

    private static ConfigurationService configurationService;

    @BeforeClass
    public static void setup(){
        configurationService = new ConfigurationServiceStub();
    }

    /**
     * Test that returns all configs but is null due to stub
     */
    @Test
    public void listAllConfigs(){
        Collection<TerminalConfiguration> configurationList = configurationService.listAllConfigs();

        Assert.assertNull(configurationList);
    }

    /**
     * Test that saves a config to the DAO (from UI)
     */
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

    /**
     * Test that performs search of a config (from UI)
     */
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

    /**
     * Test that returns all configs
     */
    @Test
    public void getAllConfigs(){
        TerminalConfigurationDAO dao = configurationService.getTerminalConfigurationDAO();

        Assert.assertNotNull(dao);
    }

    @Test
    public void getTerminalConfigurationDAO() {
        TerminalConfigurationDAO dao = configurationService.getTerminalConfigurationDAO();

        Assert.assertNotNull(dao);
    }

}