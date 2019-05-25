package gr.aueb.se.labadministration.interfaces;

import java.util.ArrayList;

import gr.aueb.se.labadministration.domain.lab.Session;

public interface HistoryActivityInterface extends ViewInterface {
    public String getId();
    public String getOption();
    public void showError(String Error);
    public void showResult(ArrayList<Session> result);
}
