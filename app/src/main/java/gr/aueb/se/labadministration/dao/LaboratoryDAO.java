package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.domain.lab.Laboratory;

import java.util.ArrayList;

/**
 * The interface the communicates with databases for the laboratories
 */
public interface LaboratoryDAO {

    /**
     * The method that saves a lab to the database
     * @param laboratory
     */
    void save(Laboratory laboratory);

    /**
     * The method that removes a lab from the database
     * @param laboratory
     */
    void remove(Laboratory laboratory);

    /**
     * The method that searches if a lab exists with that name
     * @param name
     * @return a lab or null
     */
    Laboratory findByName(String name);

    /**
     * The method that returns all the labs
     * @return
     */
    ArrayList<Laboratory> listAll();
}
