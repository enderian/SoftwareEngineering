package gr.aueb.se.labadministration.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;

public class LabServiceStub extends LabService{

    private Terminal terminal = new Terminal(null,null,null,1,1,null);

    @Override
    public LaboratoryDAO getLaboratoryDAO(){
        return new LaboratoryDAO() {
            @Override
            public void save(Laboratory laboratory) {

            }

            @Override
            public void remove(Laboratory laboratory) {

            }

            @Override
            public Laboratory findByName(String name) {
                if (name==null) return null;
                switch (name){
                    case "LAB":
                        DaySchedule daySchedule = new DaySchedule(0);
                        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                                .setName("T")
                                .setOperatingSystem("OS")
                                .setProcessor("i")
                                .setstorageCapacity(2014)
                                .setTotalMemory(1024)
                                .createTerminalConfiguration();
                        terminal = null;
                        try {
                            terminal = new TerminalBuilder()
                                    .setConfiguration(configuration)
                                    .setHostname("T")
                                    .setIpAddress(InetAddress.getByName("127.0.0.1"))
                                    .setName("T")
                                    .setPositionX(0).setPositionY(0).createTerminal();
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                        Laboratory laboratory = new Laboratory("LAB", "LOC", true);
                        laboratory.setOpen(true);
                        laboratory.addDaySchedule(daySchedule);
                        laboratory.addTerminal(terminal);
                        return laboratory;
                }
                return null;
            }

            @Override
            public ArrayList<Laboratory> listAll() {
                return null;
            }
        };
    }

    @Override
    public TerminalDAO getTerminalDAO() {
        return new TerminalDAO() {
            @Override
            public void save(Terminal terminal) {

            }

            @Override
            public void delete(Terminal terminal) {

            }

            @Override
            public Terminal findByName(String terminalName) {
                TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                        .setName("T")
                        .setOperatingSystem("OS")
                        .setProcessor("i")
                        .setstorageCapacity(2014)
                        .setTotalMemory(1024)
                        .createTerminalConfiguration();
                try {
                    terminal = new TerminalBuilder()
                            .setConfiguration(configuration)
                            .setHostname("T")
                            .setIpAddress(InetAddress.getByName("127.0.0.1"))
                            .setName("T")
                            .setPositionX(0).setPositionY(0).createTerminal();
                    terminal.setStatus(Terminal.TerminalStatus.IN_USE);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                terminal.registerSession(new Session(terminal, new User("test", null, null), null, null, null));
                return terminal;
            }

            @Override
            public Terminal findByIP(InetAddress inetAddress) {
                return null;
            }

            @Override
            public void updateStatus(Terminal terminal, Terminal.TerminalStatus status) {

            }

            @Override
            public ArrayList<Terminal> listAll() {
                return null;
            }
        };
    }

}
