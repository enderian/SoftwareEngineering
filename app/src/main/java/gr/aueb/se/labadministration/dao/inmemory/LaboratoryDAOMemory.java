package gr.aueb.se.labadministration.dao.inmemory;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.domain.lab.Laboratory;

import java.util.ArrayList;

public class LaboratoryDAOMemory implements LaboratoryDAO {

    protected static ArrayList<Laboratory> laboratories = new ArrayList<Laboratory>();

    @Override
    public void save(Laboratory laboratory) {
        laboratories.add(laboratory);
    }

    @Override
    public void remove(Laboratory laboratory) {
        laboratories.remove(laboratory);
    }

    @Override
    public Laboratory findByName(String name) {
        for (Laboratory lab : laboratories){
            if(lab.getName().equals(name)) return lab;
        }
        return null;
    }

    @Override
    public ArrayList<Laboratory> listAll() {
        return laboratories;
    }

}
