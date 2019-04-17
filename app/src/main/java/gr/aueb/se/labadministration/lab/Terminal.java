package gr.aueb.se.labadministration.lab;

import gr.aueb.se.labadministration.configurations.TerminalConfiguration;

import java.net.InetAddress;
import java.util.ArrayList;

public class Terminal {

    public enum TerminalStatus{OFFLINE, AVAILABLE, IN_USE, IN_MAINTENANCE};

    private String name;
    private String hostname;
    private InetAddress ipAddress;
    private TerminalStatus status;
    private int positionX;
    private int positionY;
    private TerminalConfiguration configuration;
    private ArrayList<Session> sessions;

    public Terminal(String name, String hostname, InetAddress ipAddress, TerminalStatus status, int positionX, int positionY, TerminalConfiguration configuration) {
        this.name = name;
        this.hostname = hostname;
        this.ipAddress = ipAddress;
        this.status = status;
        this.positionX = positionX;
        this.positionY = positionY;
        this.configuration = configuration;
        this.sessions = new ArrayList<Session>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public TerminalStatus getStatus() {
        return status;
    }

    public void setStatus(TerminalStatus status) {
        this.status = status;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public TerminalConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(TerminalConfiguration configuration) {
        this.configuration = configuration;
    }

    public boolean registerSession(Session session){
        return this.sessions.add(session);
    }
}
