package gr.aueb.se.labadministration.memorydao;

import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.dao.SoftwarePackageDAO;

import java.util.ArrayList;

public class SoftwarePackageDAOMemory implements SoftwarePackageDAO {

    protected static ArrayList<SoftwarePackage> softwarePackages = new ArrayList<SoftwarePackage>();

    static{
        softwarePackages.add(new SoftwarePackage("soft1", "make -i https://...", "rm -r /*"));
        softwarePackages.add(new SoftwarePackage("soft2", "make -i https://...", "rm -r /*"));
        softwarePackages.add(new SoftwarePackage("soft3", "make -i https://...", "rm -r /*"));
    }

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
