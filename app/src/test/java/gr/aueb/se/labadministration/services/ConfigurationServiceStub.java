package gr.aueb.se.labadministration.services;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Terminal;

public class ConfigurationServiceStub extends ConfigurationService {

    TerminalConfiguration configurations;

    @Override
    public TerminalConfigurationDAO getTerminalConfigurationDAO(){
        return new TerminalConfigurationDAO() {

            @Override
            public void save(TerminalConfiguration configuration) {
                configurations = configuration;
            }

            @Override
            public void delete(TerminalConfiguration configuration) {

            }

            @Override
            public TerminalConfiguration findByName(String configurationName) {
                if (configurationName==null) return null;
                switch (configurationName){
                    case "T":
                    TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                            .setName("T")
                            .setOperatingSystem("OS")
                            .setProcessor("i")
                            .setstorageCapacity(2014)
                            .setTotalMemory(1024)
                            .createTerminalConfiguration();
                    return configuration;
                }
                return null;
            }

            @Override
            public ArrayList<TerminalConfiguration> listAll() {
                return null;
            }
        };
    }

    public TerminalConfiguration getTests(){
        return configurations;
    }

}
