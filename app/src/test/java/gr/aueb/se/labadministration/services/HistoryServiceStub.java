package gr.aueb.se.labadministration.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.people.User;

public class HistoryServiceStub extends HistoryService {

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
                terminal.registerSession(new Session(terminal,
                        new User("d2", null, null),
                        null, null, null));
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
                terminal.registerSession(new Session(terminal,
                        new User("d2", null, null),
                        null, null, null));
                ArrayList temp = new ArrayList();
                temp.add(terminal);
                return temp;
            }
        };
    }
}
