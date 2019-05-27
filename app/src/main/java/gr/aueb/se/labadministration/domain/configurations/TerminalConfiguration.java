package gr.aueb.se.labadministration.domain.configurations;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class that defines a TerminalConfiguration
 */
public class TerminalConfiguration implements Serializable {

    private String processor;
    private String graphicsCard;
    private int storateCapacity;
    private int totalMemory;
    private String operatingSystem;
    private String name;
    private ArrayList<SoftwarePackage> softwarePackages;

    /**
     * Constructor
     * @param processor of terminal
     * @param graphicsCard of terminal
     * @param storateCapacity of terminal
     * @param totalMemory of terminal
     * @param operatingSystem of terminal
     * @param name of terminal
     */
    public TerminalConfiguration(String processor, String graphicsCard, int storateCapacity, int totalMemory, String operatingSystem, String name) {
        this.processor = processor;
        this.graphicsCard = graphicsCard;
        this.storateCapacity = storateCapacity;
        this.totalMemory = totalMemory;
        this.operatingSystem = operatingSystem;
        this.name = name;
        this.softwarePackages = new ArrayList<SoftwarePackage>();
    }

    /**
     * Copy constructor
     * @param terminalConfiguration to copy from
     */
    public TerminalConfiguration(TerminalConfiguration terminalConfiguration) {
        this.processor = terminalConfiguration.processor;
        this.graphicsCard = terminalConfiguration.graphicsCard;
        this.storateCapacity = terminalConfiguration.storateCapacity;
        this.totalMemory = terminalConfiguration.totalMemory;
        this.operatingSystem = terminalConfiguration.operatingSystem;
        this.name = terminalConfiguration.name;
        this.softwarePackages = terminalConfiguration.softwarePackages;
    }

    /**
     * Setters & Getters
     */
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

    /**
     * Method that adds a software package
     * @param softwarePackage to add
     * @return result as boolean
     */
    public boolean addSoftwarePackage(SoftwarePackage softwarePackage){
        return this.softwarePackages.add(softwarePackage);
    }

    /**
     * Method that removes a software package
     * @param softwarePackage to remove
     * @return result as boolean
     */
    public boolean removeSoftwarePackage(SoftwarePackage softwarePackage){
        return this.softwarePackages.remove(softwarePackage);
    }

    /**
     * Method that returns a list of the software packages that the terminal configuration includes
     * @return list with SoftwarePackage
     */
    public List<SoftwarePackage> listSoftwarePackages() {
        return Collections.unmodifiableList(softwarePackages);
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
