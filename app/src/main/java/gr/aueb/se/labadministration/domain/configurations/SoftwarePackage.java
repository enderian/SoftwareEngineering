package gr.aueb.se.labadministration.domain.configurations;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * The class that defines a SoftwarePackage
 */
public class SoftwarePackage implements Serializable {

    private String name;
    private String installationCommand;
    private String uninstallCommand;

    /**
     * Constructor
     * @param name of package
     * @param installationCommand of package
     * @param uninstallCommand of package
     */
    public SoftwarePackage(String name, String installationCommand, String uninstallCommand) {
        this.name = name;
        this.installationCommand = installationCommand;
        this.uninstallCommand = uninstallCommand;
    }

    /**
     * Setters & Getters
     */
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
