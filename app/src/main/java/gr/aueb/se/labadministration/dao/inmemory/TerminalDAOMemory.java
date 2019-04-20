package gr.aueb.se.labadministration.dao.inmemory;

import com.google.common.collect.Lists;

import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;
import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.lab.Terminal;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.inject.Inject;

public class TerminalDAOMemory implements TerminalDAO {

    private ArrayList<Terminal> terminals;

    @Inject
    public TerminalDAOMemory(TerminalConfigurationDAO terminalConfigurationDAO) {
        try {
            terminals = Lists.newArrayList(
                    new TerminalBuilder()
                            .setConfiguration(terminalConfigurationDAO.findByName("Common"))
                            .setHostname("CSLAB2-11")
                            .setIpAddress(InetAddress.getByName("127.0.0.1"))
                            .setName("CSLAB2-11")
                            .setPositionX(0).setPositionY(0).createTerminal()
            );
        } catch (UnknownHostException ignored) {}
    }

    @Override
    public void save(Terminal terminal) {
        terminals.add(terminal);
    }

    @Override
    public void delete(Terminal terminal) {
        terminals.remove(terminal);
    }

    @Override
    public Terminal findByName(String terminalName) {
        for (Terminal terminal : terminals) {
            if (terminal.getName().equalsIgnoreCase(terminalName)) return terminal;
        }
        return null;
    }

    @Override
    public Terminal findByIP(InetAddress inetAddress) {
        for (Terminal terminal : terminals) {
            if (terminal.getIpAddress().equals(inetAddress)) return terminal;
        }
        return null;
    }

    @Override
    public void updateStatus(Terminal terminal, Terminal.TerminalStatus status) {
        for (Terminal t : terminals) {
            if (t.equals(terminal)) terminal.setStatus(status);
        }
    }

    @Override
    public ArrayList<Terminal> listAll() {
        return terminals;
    }
}
