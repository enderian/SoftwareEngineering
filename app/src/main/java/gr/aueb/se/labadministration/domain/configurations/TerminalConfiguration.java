package gr.aueb.se.labadministration.domain.configurations;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Optional;

public class TerminalConfiguration implements Cloneable {

    private String processor;
    private String graphicsCard;
    private int storageCapacity;
    private int totalMemory;
    private String operatingSystem;
    private String name;
    private ArrayList<SoftwarePackage> softwarePackages;

    public TerminalConfiguration(String processor, String graphicsCard, Integer storageCapacity, Integer totalMemory, String operatingSystem, String name, ArrayList<SoftwarePackage> softwarePackages) {

        if (name == null) {
            throw new IllegalArgumentException("you must provide a name");
        }
        if (processor == null) {
            throw new IllegalArgumentException("you must provide a processor");
        }
        if (operatingSystem == null) {
            throw new IllegalArgumentException("you must provide an operatingSystem");
        }
        if (storageCapacity == null) {
            throw new IllegalArgumentException("you must provide storageCapacity");
        }
        if (totalMemory == null) {
            throw new IllegalArgumentException("you must provide totalMemory");
        }

        this.processor = processor;
        this.graphicsCard = graphicsCard;
        this.storageCapacity = storageCapacity;
        this.totalMemory = totalMemory;
        this.operatingSystem = operatingSystem;
        this.name = name;
        this.softwarePackages = softwarePackages == null ? Lists.newArrayList() : softwarePackages;
    }

    public TerminalConfiguration(TerminalConfiguration terminalConfiguration) {
        this.processor = terminalConfiguration.processor;
        this.graphicsCard = terminalConfiguration.graphicsCard;
        this.storageCapacity = terminalConfiguration.storageCapacity;
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

    public int getStorageCapacity() {
        return storageCapacity;
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
