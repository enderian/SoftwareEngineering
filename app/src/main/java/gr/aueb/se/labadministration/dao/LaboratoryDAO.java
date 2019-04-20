package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.lab.Laboratory;

import java.util.ArrayList;

public interface LaboratoryDAO {

    void save(Laboratory laboratory);

    void remove(Laboratory laboratory);

    Laboratory findByName(String name);

    ArrayList<Laboratory> listAll();
}
