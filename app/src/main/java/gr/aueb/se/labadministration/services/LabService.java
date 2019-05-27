package gr.aueb.se.labadministration.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;
import gr.aueb.se.labadministration.memorydao.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.memorydao.TerminalDAOMemory;

/**
 * The class that communicates with all the activities & fragments regarding labs
 */
public class LabService extends Service {

    /**
     * Binder definition
     */
    public class LabServiceBinder extends Binder {
        public LabService getService() {
            return LabService.this;
        }
    }

    private LaboratoryDAO laboratoryDAO;
    private TerminalDAO terminalDAO;

    /**
     * DAO Memory initialization
     */
    public LabService() {
        laboratoryDAO = new LaboratoryDAOMemory();
        terminalDAO = new TerminalDAOMemory();
    }

    /**
     * @return the Laboratory DAO
     */
    public LaboratoryDAO getLaboratoryDAO() {
        return laboratoryDAO;
    }

    /**
     * @return the Terminal DAO
     */
    public TerminalDAO getTerminalDAO(){
        return terminalDAO;
    }

    /**
     * Method that saves a terminal and registers it to the specified lab
     * @param laboratory to register
     * @param terminal to save
     */
    public void saveTerminal(Laboratory laboratory, Terminal terminal) {
        getTerminalDAO().save(terminal);
        Laboratory lab = getLaboratoryDAO().findByName(laboratory.getName());
        lab.addTerminal(terminal);
        getLaboratoryDAO().save(lab);
    }

    /**
     * Method that finds a terminal based on it's name
     * @param name pf terminal
     * @return Terminal or null
     */
    public Terminal findTerminal(String name){
        return getTerminalDAO().findByName(name);
    }

    /**
     * Method that returns every lab available
     * @return list of Laboratories
     */
    public List<Laboratory> listLabs(){
        return getLaboratoryDAO().listAll();
    }

    /**
     * Method that returns all the Terminals of a lab
     * @param lab
     * @return list of terminals
     */
    public List<Terminal> listComputers(Laboratory lab){
        return getLaboratoryDAO().findByName(lab.getName()).getTerminals();
    }

    /**
     * Method that returns the schedule
     * @param lab
     * @return
     */
    public List<DaySchedule> listSchedule(Laboratory lab){
        return getLaboratoryDAO().findByName(lab.getName()).getSchedule();
    }

    /**
     * Method that returns if a terminal is in use
     * @param terminal
     * @return boolean
     */
    public boolean isTerminalInUse(Terminal terminal){
        return getTerminalDAO().findByName(terminal.getName()).getStatus()==Terminal.TerminalStatus.IN_USE;
    }

    /**
     * Method that returns if a terminal is offline
     * @param terminal
     * @return boolean
     */
    public boolean isTerminalOffline(Terminal terminal){
        return getTerminalDAO().findByName(terminal.getName()).getStatus()==Terminal.TerminalStatus.OFFLINE;
    }

    /**
     * Method that returns if a terminal is in maintenance
     * @param terminal
     * @return boolean
     */
    public boolean isTerminalInMaintenance(Terminal terminal){
        return getTerminalDAO().findByName(terminal.getName()).getStatus()==Terminal.TerminalStatus.IN_MAINTENANCE;
    }

    /**
     * Method that returns the status of a terminal
     * @param terminal
     * @return Terminal.TerminalStatus as a String
     */
    public String getStatus(String terminal){
        Terminal.TerminalStatus status = getTerminalDAO().findByName(terminal).getStatus();
        if(status == Terminal.TerminalStatus.IN_USE){
            ArrayList<Session> sessions = getTerminalDAO().findByName(terminal).getSessions();
            return sessions.get(sessions.size() - 1).getUser().getUsername();
        }
        return status.toString();
    }

    /**
     * Method that performs an action that is registered through the UI
     * @param terminal to perform action
     * @param status action to perform
     * @return boolean of success or failure
     */
    public boolean terminalAction(String terminal, Terminal.TerminalStatus status){
        Terminal f_terminal = getTerminalDAO().findByName(terminal);
        if (f_terminal != null){
            f_terminal.setStatus(status);
            return true;
        }
        return false;
    }

    /**
     * Default Android Methods
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new LabServiceBinder();
    }
}
