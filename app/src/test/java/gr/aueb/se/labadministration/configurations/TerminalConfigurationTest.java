package gr.aueb.se.labadministration.configurations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TerminalConfigurationTest {

    private TerminalConfiguration terminalConfiguration;
    private SoftwarePackage softwarePackage;

    @Before
    public void TerminalConfigurationTest(){
        terminalConfiguration = new TerminalConfiguration("i7", "1070", 1024, 16384, "Dual Boot", "UIID-11WU");
        softwarePackage = new SoftwarePackage("1", "2", "3");
        terminalConfiguration.addSoftwarePackage(softwarePackage);
    }

    @Test
    public void insertSoftwarePackageReturnsTrue(){

        SoftwarePackage newPackage = new SoftwarePackage("2", "1", "2");
        boolean result = terminalConfiguration.addSoftwarePackage(newPackage);

        Assert.assertTrue(result);

    }

    @Test
    public void removeExistingPackageReturnsTrue(){

        boolean result = terminalConfiguration.removeSoftwarePackage(softwarePackage);

        Assert.assertTrue(result);

    }

    @Test
    public void removeUnknownPackageReturnsFalse(){

        SoftwarePackage testPackage = new SoftwarePackage("3", "1", "2");

        boolean result = terminalConfiguration.removeSoftwarePackage(testPackage);

        Assert.assertFalse(result);

    }

}