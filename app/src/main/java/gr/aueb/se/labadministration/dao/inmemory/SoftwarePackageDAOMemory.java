package gr.aueb.se.labadministration.dao.inmemory;

import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.dao.SoftwarePackageDAO;

import java.util.ArrayList;

public class SoftwarePackageDAOMemory implements SoftwarePackageDAO {

    protected static ArrayList<SoftwarePackage> softwarePackages = new ArrayList<SoftwarePackage>();

    @Override
    public void save(SoftwarePackage softwarePackage) {
        softwarePackages.add(softwarePackage);
    }

    @Override
    public void delete(SoftwarePackage softwarePackage) {
        softwarePackages.remove(softwarePackage);
    }

    @Override
    public SoftwarePackage findByName(String softPackageName) {
        for(SoftwarePackage sp : softwarePackages){
            if(sp.getName().equalsIgnoreCase(softPackageName)) return sp;
        }
        return null;
    }

    @Override
    public ArrayList<SoftwarePackage> listAll() {
        return softwarePackages;
    }

}
