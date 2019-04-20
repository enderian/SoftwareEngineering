package gr.aueb.se.labadministration.dao.inmemory;

import com.google.common.collect.Lists;

import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.dao.SoftwarePackageDAO;

import java.util.ArrayList;

public class SoftwarePackageDAOMemory implements SoftwarePackageDAO {

    private ArrayList<SoftwarePackage> softwarePackages = Lists.newArrayList(
            new SoftwarePackage(
                    "vscode",
                    "apt install vscode",
                    "apt remove vscode"),
            new SoftwarePackage(
                    "VisualStudio",
                    "cocoa install VisualStudio",
                    "cocoa remove VisualStudio")
    );

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
