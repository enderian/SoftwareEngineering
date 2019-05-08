package gr.aueb.se.labadministration.domain.configurations;

import java.util.ArrayList;

public class TerminalConfiguration {

    private String processor;
    private String graphicsCard;
    private int storateCapacity;
    private int totalMemory;
    private String operatingSystem;
    private String name;
    private ArrayList<SoftwarePackage> softwarePackages;

    public TerminalConfiguration(String processor, String graphicsCard, int storateCapacity, int totalMemory, String operatingSystem, String name) {
        this.processor = processor;
        this.graphicsCard = graphicsCard;
        this.storateCapacity = storateCapacity;
        this.totalMemory = totalMemory;
        this.operatingSystem = operatingSystem;
        this.name = name;
        this.softwarePackages = new ArrayList<SoftwarePackage>();
    }

    public TerminalConfiguration(TerminalConfiguration terminalConfiguration) {
        this.processor = terminalConfiguration.processor;
        this.graphicsCard = terminalConfiguration.graphicsCard;
        this.storateCapacity = terminalConfiguration.storateCapacity;
        this.totalMemory = terminalConfiguration.totalMemory;
        this.operatingSystem = terminalConfiguration.operatingSystem;
        this.name = terminalConfiguration.name;
        this.softwarePackages = terminalConfiguration.softwarePackages;
    }

    public String getProcessor() {
        return processor;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public int getStorateCapacity() {
        return storateCapacity;
    }

    public int getTotalMemory() {
        return totalMemory;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getName() {
        return name;
    }

    public boolean addSoftwarePackage(SoftwarePackage softwarePackage){
        return this.softwarePackages.add(softwarePackage);
    }

    public boolean removeSoftwarePackage(SoftwarePackage softwarePackage){
        return this.softwarePackages.remove(softwarePackage);
    }
}
