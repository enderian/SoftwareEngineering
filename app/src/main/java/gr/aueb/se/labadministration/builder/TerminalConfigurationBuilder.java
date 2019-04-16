package gr.aueb.se.labadministration.builder;

import gr.aueb.se.labadministration.configurations.TerminalConfiguration;

public class TerminalConfigurationBuilder {
    private String processor;
    private String graphicsCard;
    private int storateCapacity;
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

    public TerminalConfigurationBuilder setStorateCapacity(int storateCapacity) {
        this.storateCapacity = storateCapacity;
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
        return new TerminalConfiguration(processor, graphicsCard, storateCapacity, totalMemory, operatingSystem, name);
    }
}