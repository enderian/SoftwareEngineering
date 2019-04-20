package gr.aueb.se.labadministration.configurations;

public class SoftwarePackage {

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
}
