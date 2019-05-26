package gr.aueb.se.labadministration.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.memorydao.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.memorydao.TerminalDAOMemory;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;

public class HistoryService extends Service {

    public class HistoryServiceBinder extends Binder {
        public HistoryService getService() {
            return HistoryService.this;
        }
    }

    private TerminalDAO terminalDAO;

    public HistoryService() {
        terminalDAO = new TerminalDAOMemory();
    }

    public TerminalDAO getTerminalDAO() {
        return terminalDAO;
    }

    public List<Session> allSessions() {
        return getTerminalDAO().listAll().stream()
                .flatMap(terminal -> terminal.getSessions().stream())
                .collect(Collectors.toList());
    }

    public List<Session> findSessionsByUser(String query) {
        return getTerminalDAO().listAll().stream()
                .flatMap(terminal -> terminal.getSessions().stream())
                .filter(session -> session.getUser().getUsername().startsWith(query))
                .collect(Collectors.toList());
    }

    public List<Session> findSessionsByComputer(String query) {
        return getTerminalDAO().listAll().stream()
                .flatMap(terminal -> terminal.getSessions().stream())
                .filter(session -> session.getTerminal().getName().startsWith(query))
                .collect(Collectors.toList());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new HistoryServiceBinder();
    }
}
