package gr.aueb.se.labadministration.configurations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SoftwarePackageTest {

    private SoftwarePackage softwarePackage;

    @Before
    public void initialize(){
        this.softwarePackage = new SoftwarePackage("1", "2", "3");
    }

    @Test
    public void getName() {
        Assert.assertEquals("1", this.softwarePackage.getName());
    }

    @Test
    public void setName() {
        this.softwarePackage.setName("NewName");
    }

    @Test
    public void getInstallationCommand() {
        Assert.assertEquals("2", this.softwarePackage.getInstallationCommand());
    }

    @Test
    public void setInstallationCommand() {
        this.softwarePackage.setInstallationCommand("newInstCmd");
    }

    @Test
    public void getUninstallCommand() {
        Assert.assertEquals("3", this.softwarePackage.getUninstallCommand());
    }

    @Test
    public void setUninstallCommand() {
        this.softwarePackage.setUninstallCommand("newUnCmd");
    }
}