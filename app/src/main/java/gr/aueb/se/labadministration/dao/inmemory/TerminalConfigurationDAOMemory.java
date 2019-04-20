package gr.aueb.se.labadministration.dao.inmemory;

import com.google.common.collect.Lists;

import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;

import java.util.ArrayList;

public class TerminalConfigurationDAOMemory implements TerminalConfigurationDAO {

    private ArrayList<TerminalConfiguration> configurations = Lists.newArrayList(
            new TerminalConfigurationBuilder()
                    .setName("Common")
                    .setOperatingSystem("Windows")
                    .setProcessor("i5")
                    .setStorageCapacity(2014)
                    .setTotalMemory(1024)
                    .createTerminalConfiguration(),
            new TerminalConfigurationBuilder()
                    .setName("Graphics")
                    .setOperatingSystem("Linux")
                    .setProcessor("i7")
                    .setStorageCapacity(2014)
                    .setTotalMemory(1024)
                    .createTerminalConfiguration()
    );

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
