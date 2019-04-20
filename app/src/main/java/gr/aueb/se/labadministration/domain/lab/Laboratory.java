package gr.aueb.se.labadministration.domain.lab;

import com.google.common.collect.Lists;

import gr.aueb.se.labadministration.domain.people.Administrator;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;

import java.util.ArrayList;

public class Laboratory implements Cloneable {

    private String name;
    private String location;
    private boolean isOpen;

    private ArrayList<DaySchedule> schedule;
    private ArrayList<Terminal> terminals;
    private ArrayList<Administrator> administrators;

    public Laboratory(String name, String location, boolean isOpen, ArrayList<DaySchedule> schedule, ArrayList<Terminal> terminals, ArrayList<Administrator> administrators) {
        this.name = name;
        this.location = location;
        this.isOpen = isOpen;
        this.schedule = schedule == null ? Lists.newArrayList() : schedule;
        this.terminals = terminals == null ? Lists.newArrayList() : terminals;
        this.administrators = administrators == null ? Lists.newArrayList() : administrators;
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

    public boolean addDaySchedule(DaySchedule daySchedule){
        return this.schedule.add(daySchedule);
    }

    public boolean removeDaySchedule(DaySchedule daySchedule){
        return this.schedule.remove(daySchedule);
    }

    public boolean addTerminal(Terminal terminal){
        return this.terminals.add(terminal);
    }

    public boolean removeTerminal(Terminal terminal){
        return this.terminals.remove(terminal);
    }

    public boolean addAdministrator(Administrator administrator){
        return this.administrators.add(administrator);
    }

    public boolean removeAdministrator(Administrator administrator){
        return this.administrators.remove(administrator);
    }

}
