package gr.aueb.se.labadministration.builder;

import gr.aueb.se.labadministration.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.lab.Terminal;

import java.net.InetAddress;

public class TerminalBuilder {
    private String name;
    private String hostname;
    private InetAddress ipAddress;
    private Terminal.TerminalStatus status;
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

    public TerminalBuilder setStatus(Terminal.TerminalStatus status) {
        this.status = status;
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

    public Terminal createTerminal() {
        return new Terminal(name, hostname, ipAddress, status, positionX, positionY, configuration);
    }
}