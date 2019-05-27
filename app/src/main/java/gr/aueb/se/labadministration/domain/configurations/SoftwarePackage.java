package gr.aueb.se.labadministration.domain.configurations;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class SoftwarePackage implements Serializable {

    private String name;
    private String installationCommand;
    private String uninstallCommand;

    public SoftwarePackage(String name, String installationCommand, String uninstallCommand) {
        this.name = name;
        this.installationCommand = installationCommand;
        this.uninstallCommand = uninstallCommand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstallationCommand() {
        return installationCommand;
    }

    public void setInstallationCommand(String installationCommand) {
        this.installationCommand = installationCommand;
    }

    public String getUninstallCommand() {
        return uninstallCommand;
    }

    public void setUninstallCommand(String uninstallCommand) {
        this.uninstallCommand = uninstallCommand;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
