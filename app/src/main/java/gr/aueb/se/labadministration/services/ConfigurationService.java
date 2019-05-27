package gr.aueb.se.labadministration.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.List;

import gr.aueb.se.labadministration.dao.TerminalConfigurationDAO;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.memorydao.TerminalConfigurationDAOMemory;

/**
 * The class that communicates with all the activities & fragments regarding terminal configurations
 */
public class ConfigurationService extends Service {

    /**
     * Binder definition
     */
    public class ConfigurationServiceBinder extends Binder {
        public ConfigurationService getService(){
            return ConfigurationService.this;
        }
    }

    private TerminalConfigurationDAO terminalConfigurationDAO;

    /**
     * DAO Memory initialization
     */
    public ConfigurationService(){
        terminalConfigurationDAO = new TerminalConfigurationDAOMemory();
    }

    /**
     * Method that saves the new configuration that is given from the UI
     * @param configuration
     */
    public void saveConfiguration(TerminalConfiguration configuration){
        getTerminalConfigurationDAO().save(configuration);
    }

    /**
     * Method that finds the configuration that is given from the UI
     * @param configuration
     */
    public TerminalConfiguration findConfig(TerminalConfiguration configuration){
        return getTerminalConfigurationDAO().findByName(configuration.getName());
    }

    /**
     * Method that displays all the available configs to the UI
     * @return list of configs
     */
    public Collection<TerminalConfiguration> listAllConfigs(){
        return getTerminalConfigurationDAO().listAll();
    }

    /**
     * @return the DAO
     */
    public TerminalConfigurationDAO getTerminalConfigurationDAO(){
        return terminalConfigurationDAO;
    }

    /**
     * Default Android Methods
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ConfigurationServiceBinder();
    }
}
