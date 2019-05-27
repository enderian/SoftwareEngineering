package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;

import java.util.ArrayList;

/**
 * The interface the communicates with databases for the software packages
 */
public interface SoftwarePackageDAO {

    /**
     * The method that saves a software package to the database
     * @param softwarePackage
     */
    void save(SoftwarePackage softwarePackage);

    /**
     *  The method that removes a software package from the database
     * @param softwarePackage
     */
    void delete(SoftwarePackage softwarePackage);

    /**
     * The method that searches if a software package exists with that name
     * @param softPackageName
     * @return software package or null
     */
    SoftwarePackage findByName(String softPackageName);

    /**
     * The method that returns all the software packages
     * @return
     */
    ArrayList<SoftwarePackage> listAll();

}
