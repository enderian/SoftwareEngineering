package gr.aueb.se.labadministration.memorydao;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.lab.Laboratory;

import java.util.ArrayList;

public class LaboratoryDAOMemory implements LaboratoryDAO {

    protected ArrayList<Laboratory> laboratories = new ArrayList<Laboratory>();

    @Override
    public void save(Laboratory laboratory) {
        this.laboratories.add(laboratory);
    }

    @Override
    public void remove(Laboratory laboratory) {
        this.laboratories.remove(laboratory);
    }

    @Override
    public Laboratory findByName(String name) {
        for (Laboratory lab : laboratories){
            if(lab.getName().equalsIgnoreCase(name)) return lab;
        }
        return null;
    }

    @Override
    public ArrayList<Laboratory> listAll() {
        return this.laboratories;
    }

}
