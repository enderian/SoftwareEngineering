package gr.aueb.se.labadministration.domain.builder;

import java.util.ArrayList;

import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.people.Administrator;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;

public class LaboratoryBuilder {

    private String name;
    private String location;
    private boolean isOpen;
    private ArrayList<DaySchedule> schedule;
    private ArrayList<Terminal> terminals;
    private ArrayList<Administrator> administrators;

    public LaboratoryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LaboratoryBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public LaboratoryBuilder setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
        return this;
    }

    public LaboratoryBuilder setSchedule(ArrayList<DaySchedule> schedule) {
        this.schedule = schedule;
        return this;
    }

    public LaboratoryBuilder setTerminals(ArrayList<Terminal> terminals) {
        this.terminals = terminals;
        return this;
    }

    public LaboratoryBuilder setAdministrators(ArrayList<Administrator> administrators) {
        this.administrators = administrators;
        return this;
    }

    public Laboratory createLaboratory() {
        return new Laboratory(name, location, isOpen, schedule, terminals, administrators);
    }
}