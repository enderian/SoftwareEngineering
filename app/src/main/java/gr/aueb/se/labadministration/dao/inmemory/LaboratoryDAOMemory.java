package gr.aueb.se.labadministration.dao.inmemory;

import com.google.common.collect.Lists;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.domain.builder.LaboratoryBuilder;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.people.Administrator;
import gr.aueb.se.labadministration.domain.people.User;

import java.util.ArrayList;

import javax.inject.Inject;

public class LaboratoryDAOMemory implements LaboratoryDAO {

    private ArrayList<Laboratory> laboratories;

    @Inject
    public LaboratoryDAOMemory(TerminalDAO terminalDAO, UserDAO userDAO) {
        laboratories  = Lists.newArrayList(
            new LaboratoryBuilder().
                    setName("CSLAB-1").
                    setIsOpen(true).
                    setLocation("Derigni 2nd Floor").
                    createLaboratory(),
            new LaboratoryBuilder().
                    setName("CSLAB-2").
                    setIsOpen(true).
                    setLocation("Derigni 3nd Floor").
                    setAdministrators(Lists.newArrayList((Administrator) userDAO.find("p3150133"))).
                    setTerminals(Lists.newArrayList(terminalDAO.findByName("CSLAB2-11"))).
                    createLaboratory()
        );
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
