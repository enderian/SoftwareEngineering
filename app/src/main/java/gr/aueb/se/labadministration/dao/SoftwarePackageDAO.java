package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.configurations.SoftwarePackage;

import java.util.ArrayList;

public interface SoftwarePackageDAO {

    void save(SoftwarePackage softwarePackage);

    void delete(SoftwarePackage softwarePackage);

    SoftwarePackage findByName(String softPackageName);

    ArrayList<SoftwarePackage> listAll();

}
