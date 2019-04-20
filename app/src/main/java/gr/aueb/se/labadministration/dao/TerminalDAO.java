package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.domain.lab.Terminal;

import java.net.InetAddress;
import java.util.ArrayList;

public interface TerminalDAO {

    void save(Terminal terminal);

    void delete(Terminal terminal);

    Terminal findByName(String terminalName);

    Terminal findByIP(InetAddress inetAddress);

    void updateStatus(Terminal terminal, Terminal.TerminalStatus status);

    ArrayList<Terminal> listAll();

}
