package gr.aueb.se.labadministration.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.schedule.DaySchedule;

public class LabServiceStub extends LabService{

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
                        Terminal terminal = null;
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

}
