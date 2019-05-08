package gr.aueb.se.labadministration.domain.builder;

import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;

public class TerminalConfigurationBuilder {
    private String processor;
    private String graphicsCard;
    private int storageCapacity;
    private int totalMemory;
    private String operatingSystem;
    private String name;

    public TerminalConfigurationBuilder setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public TerminalConfigurationBuilder setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
    }

    public TerminalConfigurationBuilder setstorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
        return this;
    }

    public TerminalConfigurationBuilder setTotalMemory(int totalMemory) {
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

    public TerminalConfiguration createTerminalConfiguration() {
        if (processor == null) return null;
        if (operatingSystem == null) return null;
        if (name == null) return null;
        if (storageCapacity == 0 || totalMemory == 0) return null;
        return new TerminalConfiguration(processor, graphicsCard, storageCapacity, totalMemory, operatingSystem, name);
    }
}