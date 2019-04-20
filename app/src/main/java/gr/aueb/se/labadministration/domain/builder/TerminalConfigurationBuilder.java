package gr.aueb.se.labadministration.domain.builder;

import java.util.ArrayList;

import gr.aueb.se.labadministration.domain.configurations.SoftwarePackage;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;

public class TerminalConfigurationBuilder {

    private String processor;
    private String graphicsCard;
    private Integer storageCapacity;
    private Integer totalMemory;
    private String operatingSystem;
    private String name;
    private ArrayList<SoftwarePackage> softwarePackages;
    private TerminalConfiguration terminalConfiguration;

    public TerminalConfigurationBuilder setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public TerminalConfigurationBuilder setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
    }

    public TerminalConfigurationBuilder setStorageCapacity(Integer storageCapacity) {
        this.storageCapacity = storageCapacity;
        return this;
    }

    public TerminalConfigurationBuilder setTotalMemory(Integer totalMemory) {
        this.totalMemory = totalMemory;
        return this;
    }

    public TerminalConfigurationBuilder setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public TerminalConfigurationBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TerminalConfigurationBuilder setSoftwarePackages(ArrayList<SoftwarePackage> softwarePackages) {
        this.softwarePackages = softwarePackages;
        return this;
    }

    public TerminalConfigurationBuilder setTerminalConfiguration(TerminalConfiguration terminalConfiguration) {
        this.terminalConfiguration = terminalConfiguration;
        return this;
    }

    public TerminalConfiguration createTerminalConfiguration() {
        return new TerminalConfiguration(processor, graphicsCard, storageCapacity, totalMemory, operatingSystem, name, softwarePackages);
    }
}