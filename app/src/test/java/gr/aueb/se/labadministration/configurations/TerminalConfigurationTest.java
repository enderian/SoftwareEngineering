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

    @Test
    public void getProcessor() {
        Assert.assertEquals("i7", terminalConfiguration.getProcessor());
    }

    @Test
    public void getGraphicsCard() {
        Assert.assertEquals("1070", terminalConfiguration.getGraphicsCard());
    }

    @Test
    public void getStorateCapacity() {
        Assert.assertEquals(1024, terminalConfiguration.getStorateCapacity());
    }

    @Test
    public void getTotalMemory() {
        Assert.assertEquals(16384, terminalConfiguration.getTotalMemory());
    }

    @Test
    public void getOperatingSystem() {
        Assert.assertEquals("Dual Boot", terminalConfiguration.getOperatingSystem());
    }

    @Test
    public void getName() {
        Assert.assertEquals("UIID-11WU", terminalConfiguration.getName());
    }

    @Test
    public void copyConstructorTest(){

        TerminalConfiguration test = new TerminalConfiguration(this.terminalConfiguration);

        Assert.assertNotNull(test);

    }
}