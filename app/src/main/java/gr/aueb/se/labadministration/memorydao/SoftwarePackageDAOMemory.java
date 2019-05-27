package gr.aueb.se.labadministration.memorydao;

import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.dao.SoftwarePackageDAO;

import java.util.ArrayList;

/**
 * The interface the communicates with memory for the software packages
 */
public class SoftwarePackageDAOMemory implements SoftwarePackageDAO {

    protected static ArrayList<SoftwarePackage> softwarePackages = new ArrayList<SoftwarePackage>();

    static{
        softwarePackages.add(new SoftwarePackage("soft1", "make -i https://...", "rm -r /*"));
        softwarePackages.add(new SoftwarePackage("soft2", "make -i https://...", "rm -r /*"));
        softwarePackages.add(new SoftwarePackage("soft3", "make -i https://...", "rm -r /*"));
    }

    /**
     * The method that saves a software package to the memory
     * @param softwarePackage
     */
    @Override
    public void save(SoftwarePackage softwarePackage) {
        softwarePackages.add(softwarePackage);
    }

    /**
     *  The method that removes a software package from the memory
     * @param softwarePackage
     */
    @Override
    public void delete(SoftwarePackage softwarePackage) {
        softwarePackages.remove(softwarePackage);
    }

    /**
     * The method that searches if a software package exists with that name
     * @param softPackageName
     * @return software package or null
     */
    @Override
    public SoftwarePackage findByName(String softPackageName) {
        for(SoftwarePackage sp : softwarePackages){
            if(sp.getName().equalsIgnoreCase(softPackageName)) return sp;
        }
        return null;
    }

    /**
     * The method that returns all the software packages
     * @return
     */
    @Override
    public ArrayList<SoftwarePackage> listAll() {
        return softwarePackages;
    }

}
