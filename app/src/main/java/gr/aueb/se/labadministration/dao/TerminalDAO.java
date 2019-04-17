package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.lab.Terminal;

import java.net.InetAddress;
import java.util.ArrayList;

public interface TerminalDAO {

    void save(Terminal terminal);

    void delete(Terminal terminal);

    Terminal findByName(String terminalName);

    Terminal findByIP(InetAddress inetAddress);

    ArrayList<Terminal> findByLab(String labName);

    ArrayList<Terminal> listAll();

}
