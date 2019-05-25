package gr.aueb.se.labadministration.memorydao;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.domain.lab.Laboratory;

import java.util.ArrayList;

public class LaboratoryDAOMemory implements LaboratoryDAO {

    protected static ArrayList<Laboratory> laboratories = new ArrayList<Laboratory>();

    static {
        laboratories.add(new Laboratory("Lab1", "Aueb", true));
        laboratories.add(new Laboratory("Lab2", "Aueb", false));
        laboratories.add(new Laboratory("Lab3", "Aueb at Troy", true));
    }

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
