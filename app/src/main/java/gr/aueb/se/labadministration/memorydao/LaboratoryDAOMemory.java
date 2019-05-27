package gr.aueb.se.labadministration.memorydao;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The interface the communicates with memory for the laboratories
 */
public class LaboratoryDAOMemory implements LaboratoryDAO {

    protected static ArrayList<Laboratory> laboratories = new ArrayList<Laboratory>();

    static {
        laboratories.add(new Laboratory("Lab1", "Aueb", true));
        laboratories.add(new Laboratory("Lab2", "Aueb", false));
        laboratories.add(new Laboratory("Lab3", "Aueb at Troy", true));

        laboratories.get(0).setTerminals(TerminalDAOMemory.terminals);
        laboratories.get(1).setTerminals(new ArrayList<>(TerminalDAOMemory.terminals.subList(0,2)));
        laboratories.get(2).setTerminals(new ArrayList<>(TerminalDAOMemory.terminals.subList(1,3)));

        laboratories.get(0).setSchedule(new ArrayList<>(Arrays.asList(new DaySchedule(1), new DaySchedule(2))));
        laboratories.get(1).setSchedule(new ArrayList<>(Arrays.asList(new DaySchedule(4), new DaySchedule(3))));
        laboratories.get(2).setSchedule(new ArrayList<>(Arrays.asList(new DaySchedule(1), new DaySchedule(5))));
    }

    /**
     * The method that saves a lab to the memory
     * @param laboratory
     */
    @Override
    public void save(Laboratory laboratory) {
        laboratories.add(laboratory);
    }

    /**
     * The method that removes a lab from the memory
     * @param laboratory
     */
    @Override
    public void remove(Laboratory laboratory) {
        laboratories.remove(laboratory);
    }

    /**
     * The method that adds a terminal to the lab
     * @param terminal
     */
    @Override
    public void addTerminal(Laboratory laboratory, Terminal terminal) {
        for (Laboratory lab : laboratories){
            if(lab.getName().equals(laboratory.getName())) {
                lab.addTerminal(terminal);
            }
        }
    }

    /**
     * The method that searches if a lab exists with that name
     * @param name
     * @return a lab or null
     */
    @Override
    public Laboratory findByName(String name) {
        for (Laboratory lab : laboratories){
            if(lab.getName().equals(name)) return lab;
        }
        return null;
    }

    /**
     * The method that returns all the labs
     * @return
     */
    @Override
    public ArrayList<Laboratory> listAll() {
        return laboratories;
    }

}
