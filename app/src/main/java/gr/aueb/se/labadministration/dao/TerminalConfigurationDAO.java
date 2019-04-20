package gr.aueb.se.labadministration.dao;

import gr.aueb.se.labadministration.configurations.TerminalConfiguration;

import java.util.ArrayList;

public interface TerminalConfigurationDAO {

    void save(TerminalConfiguration configuration);

    void delete(TerminalConfiguration configuration);

    TerminalConfiguration findByName(String configurationName);

    ArrayList<TerminalConfiguration> listAll();

}
