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

public class ConfigurationService extends Service {

    public class ConfigurationServiceBinder extends Binder {
        public ConfigurationService getService(){
            return ConfigurationService.this;
        }
    }

    private TerminalConfigurationDAO terminalConfigurationDAO;

    public ConfigurationService(){
        terminalConfigurationDAO = new TerminalConfigurationDAOMemory();
    }

    public void saveConfiguration(TerminalConfiguration configuration){
        getTerminalConfigurationDAO().save(configuration);
    }

    public TerminalConfiguration findConfig(TerminalConfiguration configuration){
        return getTerminalConfigurationDAO().findByName(configuration.getName());
    }

    public Collection<TerminalConfiguration> listAllConfigs(){
        return getTerminalConfigurationDAO().listAll();
    }

    public TerminalConfigurationDAO getTerminalConfigurationDAO(){
        return terminalConfigurationDAO;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ConfigurationServiceBinder();
    }
}
