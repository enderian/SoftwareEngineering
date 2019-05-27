package gr.aueb.se.labadministration.memorydao;

import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * The interface the communicates with memory for the terminal configurations
 */
public class TerminalConfigurationDAOMemory implements TerminalConfigurationDAO {

    protected static HashMap<String, TerminalConfiguration> configurations = new HashMap<>();

    static {
        configurations.put("CSLAB1", new TerminalConfigurationBuilder()
                .setName("CSLAB1")
                .setOperatingSystem("Windows 10")
                .setProcessor("i7")
                .setstorageCapacity(2014)
                .setTotalMemory(2048)
                .createTerminalConfiguration());
        configurations.put("CSLAB2", new TerminalConfigurationBuilder()
                .setName("CSLAB2")
                .setOperatingSystem("Ubuntu 16.04 LTS")
                .setProcessor("i5")
                .setstorageCapacity(2014)
                .setTotalMemory(1024)
                .createTerminalConfiguration());
    }

    /**
     * The method that saves a terminal configuration to the memory
     * @param configuration
     */
    @Override
    public void save(TerminalConfiguration configuration) {
        configurations.put(configuration.getName(), configuration);
    }

    /**
     * The method that removes a terminal configuration from the memory
     * @param configuration
     */
    @Override
    public void delete(TerminalConfiguration configuration) {
        configurations.remove(configuration.getName());
    }

    /**
     * The method that searches if a terminal configuration exists with that name
     * @param configurationName
     * @return terminal configuration or null
     */
    @Override
    public TerminalConfiguration findByName(String configurationName) {
        for(TerminalConfiguration conf : configurations.values()){
            if(conf.getName().equalsIgnoreCase(configurationName)) return conf;
        }
        return null;
    }

    /**
     * The method that returns all terminal configurations
     * @return
     */
    @Override
    public Collection<TerminalConfiguration> listAll() {
        return configurations.values();
    }
}
