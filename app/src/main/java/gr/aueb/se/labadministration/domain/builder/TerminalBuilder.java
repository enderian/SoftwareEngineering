package gr.aueb.se.labadministration.domain.builder;

import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Laboratory;
import gr.aueb.se.labadministration.domain.lab.Terminal;

import java.net.InetAddress;
import java.util.Objects;

/**
 * The class that builds a terminal
 */
public class TerminalBuilder {

    private String name;
    private String hostname;
    private InetAddress ipAddress;
    private int positionX;
    private int positionY;
    private TerminalConfiguration configuration;

    public TerminalBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TerminalBuilder setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public TerminalBuilder setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public TerminalBuilder setPositionX(int positionX) {
        this.positionX = positionX;
        return this;
    }

    public TerminalBuilder setPositionY(int positionY) {
        this.positionY = positionY;
        return this;
    }

    public TerminalBuilder setConfiguration(TerminalConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    /**
     * The method that creates a terminal
     * @return Terminal
     */
    public Terminal createTerminal() {
        Objects.requireNonNull(name, "name is null");
        Objects.requireNonNull(hostname, "hostname is null");
        Objects.requireNonNull(ipAddress, "ipAddress is null");
        Objects.requireNonNull(configuration, "configuration is null");
        return new Terminal(name, hostname, ipAddress, positionX, positionY, configuration);
    }
}