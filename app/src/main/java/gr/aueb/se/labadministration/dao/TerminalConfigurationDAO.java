package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The interface the communicates with databases for the terminal configurations
 */

public interface TerminalConfigurationDAO {

    /**
     * The method that saves a terminal configuration to the database
     * @param configuration
     */
    void save(TerminalConfiguration configuration);

    /**
     * The method that removes a terminal configuration from the database
     * @param configuration
     */
    void delete(TerminalConfiguration configuration);

    /**
     * The method that searches if a terminal configuration exists with that name
     * @param configurationName
     * @return terminal configuration or null
     */
    TerminalConfiguration findByName(String configurationName);

    /**
     * The method that returns all terminal configurations
     * @return
     */
    Collection<TerminalConfiguration> listAll();

}
