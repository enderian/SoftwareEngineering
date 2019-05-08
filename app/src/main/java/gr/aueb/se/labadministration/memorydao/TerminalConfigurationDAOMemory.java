package gr.aueb.se.labadministration.memorydao;

import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;

import java.util.ArrayList;

public class TerminalConfigurationDAOMemory implements TerminalConfigurationDAO {

    protected static ArrayList<TerminalConfiguration> configurations = new ArrayList<TerminalConfiguration>();

    @Override
    public void save(TerminalConfiguration configuration) {
        configurations.add(configuration);
    }

    @Override
    public void delete(TerminalConfiguration configuration) {
        configurations.remove(configuration);
    }

    @Override
    public TerminalConfiguration findByName(String configurationName) {
        for(TerminalConfiguration conf : configurations){
            if(conf.getName().equalsIgnoreCase(configurationName)) return conf;
        }
        return null;
    }

    @Override
    public ArrayList<TerminalConfiguration> listAll() {
        return configurations;
    }
}
