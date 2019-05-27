package gr.aueb.se.labadministration.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;
import java.util.stream.Collectors;

import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.memorydao.TerminalDAOMemory;

/**
 * The class that communicates with all the activities & fragments regarding history
 */
public class HistoryService extends Service {

    /**
     * Binder definition
     */
    public class HistoryServiceBinder extends Binder {
        public HistoryService getService() {
            return HistoryService.this;
        }
    }

    private TerminalDAO terminalDAO;

    /**
     * DAO Memory initialization
     */
    public HistoryService() {
        terminalDAO = new TerminalDAOMemory();
    }

    /**
     * @return the DAO
     */
    public TerminalDAO getTerminalDAO() {
        return terminalDAO;
    }

    /**
     * Method that returns all the sessions
     * @return list of sessions
     */
    public List<Session> allSessions() {
        return getTerminalDAO().listAll().stream()
                .flatMap(terminal -> terminal.getSessions().stream())
                .collect(Collectors.toList());
    }

    /**
     * Method that returns all the sessions of specific user
     * @param query
     * @return list of sessions
     */
    public List<Session> findSessionsByUser(String query) {
        return getTerminalDAO().listAll().stream()
                .flatMap(terminal -> terminal.getSessions().stream())
                .filter(session -> session.getUser().getUsername().startsWith(query))
                .collect(Collectors.toList());
    }

    /**
     * Method that returns all the sessions of specific terminal
     * @param query
     * @return list of sessions
     */
    public List<Session> findSessionsByComputer(String query) {
        return getTerminalDAO().listAll().stream()
                .flatMap(terminal -> terminal.getSessions().stream())
                .filter(session -> session.getTerminal().getName().startsWith(query))
                .collect(Collectors.toList());
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
        return new HistoryServiceBinder();
    }
}
