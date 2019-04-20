package gr.aueb.se.labadministration;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import gr.aueb.se.labadministration.dao.LaboratoryDAO;
import gr.aueb.se.labadministration.dao.SoftwarePackageDAO;
import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;
import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.dao.inmemory.LaboratoryDAOMemory;
import gr.aueb.se.labadministration.dao.inmemory.SoftwarePackageDAOMemory;
import gr.aueb.se.labadministration.dao.inmemory.TerminalConfigurationDAOMemory;
import gr.aueb.se.labadministration.dao.inmemory.TerminalDAOMemory;
import gr.aueb.se.labadministration.dao.inmemory.UserDAOMemory;

public class LabAdministrationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LaboratoryDAO.class).to(LaboratoryDAOMemory.class).in(Singleton.class);
        bind(SoftwarePackageDAO.class).to(SoftwarePackageDAOMemory.class).in(Singleton.class);
        bind(TerminalConfigurationDAO.class).to(TerminalConfigurationDAOMemory.class).in(Singleton.class);
        bind(TerminalDAO.class).to(TerminalDAOMemory.class).in(Singleton.class);
        bind(UserDAO.class).to(UserDAOMemory.class).in(Singleton.class);
    }
}
