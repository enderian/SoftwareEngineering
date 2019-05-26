package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * The interface the communicates with databases for the terminals
 */

public interface TerminalDAO {

    /**
     * The method that saves a terminal to the database
     * @param terminal
     */
    void save(Terminal terminal);

    /**
     * The method that removes a terminal from the database
     * @param terminal
     */
    void delete(Terminal terminal);

    /**
     * The method that searches if a terminal exists with that name
     * @param terminalName
     * @return terminal or null
     */
    Terminal findByName(String terminalName);

    /**
     * The method that searches if a terminal exists with that ip
     * @param inetAddress
     * @return terminal or null
     */
    Terminal findByIP(InetAddress inetAddress);

    /**
     * The method that saves changes to the terminal
     * @param terminal
     * @param status of the terminal that moment
     */
    void updateStatus(Terminal terminal, Terminal.TerminalStatus status);

    /**
     * Returns all terminals
     * @return
     */
    ArrayList<Terminal> listAll();

}
