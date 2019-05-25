package gr.aueb.se.labadministration.presenter;

import android.util.Log;

import java.util.ArrayList;

import gr.aueb.se.labadministration.activities.HistoryActivity;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.interfaces.HistoryActivityInterface;
import gr.aueb.se.labadministration.interfaces.Presenter;
import gr.aueb.se.labadministration.memorydao.TerminalDAOMemory;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;

public class HistoryPresenter implements Presenter {

    private HistoryActivityInterface historyActivity;
    private ArrayList<Session> sessionsResult = new ArrayList<>();

    public HistoryPresenter(HistoryActivityInterface historyActivity) {
        this.historyActivity = historyActivity;
    }

    public void searchEvent(){

        String option = historyActivity.getOption();

        String id = historyActivity.getId();

        if(option.equals("computer")){
            TerminalDAOMemory dao = new TerminalDAOMemory();
            Terminal terminal = dao.findByName(id);
            if (terminal == null){
                historyActivity.showError("Name of computer doesn't exist.");
                return;
            }
            sessionsResult.addAll(terminal.getSessions());

        }else{ // criteria.equals(user)
            UserDAOMemory dao = new UserDAOMemory();
            User user = dao.find(id);
            if (user == null){
                historyActivity.showError("Username doesn't exist.");
                return;
            }
            sessionsResult.addAll(dao.listAllSessions(user));
        }
        historyActivity.showResult(sessionsResult);
        historyActivity.close();
    }

    @Override
    public void start() {
        historyActivity.setPresenter(this);
        historyActivity.open();
    }
}
