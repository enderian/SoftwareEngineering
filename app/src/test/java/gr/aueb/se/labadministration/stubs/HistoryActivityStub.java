package gr.aueb.se.labadministration.stubs;

import android.util.Log;

import java.util.ArrayList;

import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.interfaces.HistoryActivityInterface;
import gr.aueb.se.labadministration.interfaces.Presenter;
import gr.aueb.se.labadministration.presenter.HistoryPresenter;

public class HistoryActivityStub implements HistoryActivityInterface {

    String id, option;
    HistoryPresenter presenter;
    boolean hasResults = false;
    boolean showError;
    int numberOfSessions;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getOption() {
        return option;
    }

    @Override
    public void showError(String error) {
        showError = true;
    }

    @Override
    public void showResult(ArrayList<Session> result) {
        hasResults = true;
        numberOfSessions = result.size();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = (HistoryPresenter) presenter;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public HistoryPresenter getPresenter() {
        return presenter;
    }

    public boolean isResults() {
        return hasResults;
    }

    public boolean isError() {
        return showError;
    }

    public int getNumberOfSessions() {
        return numberOfSessions;
    }
}
