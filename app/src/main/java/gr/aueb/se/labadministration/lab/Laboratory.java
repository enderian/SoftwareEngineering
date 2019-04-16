package gr.aueb.se.labadministration.lab;

import gr.aueb.se.labadministration.people.Administrator;
import gr.aueb.se.labadministration.schedule.DaySchedule;

import java.util.ArrayList;

public class Laboratory {

    private String name;
    private String location;
    private boolean isOpen;
    private ArrayList<DaySchedule> schedule;
    private ArrayList<Terminal> terminals;
    private ArrayList<Administrator> administrators;

    public Laboratory(String name, String location, boolean isOpen) {
        this.name = name;
        this.location = location;
        this.isOpen = isOpen;
        this.schedule = new ArrayList<DaySchedule>();
        this.terminals = new ArrayList<Terminal>();
        this.administrators = new ArrayList<Administrator>();
    }

    public Laboratory(Laboratory laboratory) {
        this.name = laboratory.name;
        this.location = laboratory.location;
        this.isOpen = laboratory.isOpen;
        this.schedule = laboratory.schedule;
        this.terminals = laboratory.terminals;
        this.administrators = laboratory.administrators;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public ArrayList<DaySchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<DaySchedule> schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(ArrayList<Terminal> terminals) {
        this.terminals = terminals;
    }

    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(ArrayList<Administrator> administrators) {
        this.administrators = administrators;
    }


}
