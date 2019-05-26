package gr.aueb.se.labadministration.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;
import gr.aueb.se.labadministration.memorydao.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.memorydao.TerminalDAOMemory;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.utilities.ExpandableListAdapter;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class LabService extends Service {

    public class LabServiceBinder extends Binder {
        public LabService getService() {
            return LabService.this;
        }
    }

    private LaboratoryDAO laboratoryDAO;
    private TerminalDAO terminalDAO;

    public LabService() {
        laboratoryDAO = new LaboratoryDAOMemory();
        terminalDAO = new TerminalDAOMemory();
    }

    public LaboratoryDAO getLaboratoryDAO() {
        return laboratoryDAO;
    }

    public TerminalDAO getTerminalDAO(){
        return terminalDAO;
    }

    public List<Laboratory> listLabs(){
        return laboratoryDAO.listAll();
    }

    public List<Terminal> listComputers(Laboratory lab){
        return laboratoryDAO.findByName(lab.getName()).getTerminals();
    }

    public List<DaySchedule> listSchedule(Laboratory lab){
        return laboratoryDAO.findByName(lab.getName()).getSchedule();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new LabServiceBinder();
    }
}
