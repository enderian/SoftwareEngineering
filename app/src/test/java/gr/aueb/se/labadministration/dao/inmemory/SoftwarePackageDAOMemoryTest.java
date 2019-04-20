package gr.aueb.se.labadministration.dao.inmemory;

import org.junit.Assert;
import org.junit.Test;

import gr.aueb.se.labadministration.dao.SoftwarePackageDAO;
import gr.aueb.se.labadministration.dao.inmemory.SoftwarePackageDAOMemory;
import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;

public class SoftwarePackageDAOMemoryTest {

    private SoftwarePackageDAO softwarePackageDAO = new SoftwarePackageDAOMemory();

    @Test
    public void findSoftwarePackage() {
        Assert.assertNotNull(softwarePackageDAO.findByName("vscode"));

        Assert.assertNull(softwarePackageDAO.findByName("intellij"));
    }

    @Test
    public void saveSoftwarePackage() {
        softwarePackageDAO.save(new SoftwarePackage("AndroidStudio", "pacman -S android_studio", ""));

        Assert.assertNotNull(softwarePackageDAO.findByName("AndroidStudio"));
    }

    @Test
    public void deleteSoftwarePackage() {
        softwarePackageDAO.delete(softwarePackageDAO.findByName("VisualStudio"));

        Assert.assertNull(softwarePackageDAO.findByName("VisualStudio"));
    }

    @Test
    public void listAll() {
        Assert.assertFalse(softwarePackageDAO.listAll().isEmpty());
    }

}
