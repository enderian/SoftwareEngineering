package gr.aueb.se.labadministration.domain.lab;

import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * The class that defines a Terminal
 */
public class Terminal implements Serializable {

    public enum TerminalStatus{OFFLINE, AVAILABLE, IN_USE, IN_MAINTENANCE};

    private String name;
    private String hostname;
    private InetAddress ipAddress;
    private TerminalStatus status;
    private int positionX;
    private int positionY;
    private TerminalConfiguration configuration;
    private ArrayList<Session> sessions;

    /**
     * Constructor
     * @param name of terminal
     * @param hostname of terminal
     * @param ipAddress of terminal
     * @param positionX of terminal
     * @param positionY of terminal
     * @param configuration of terminal
     */
    public Terminal(String name, String hostname, InetAddress ipAddress, int positionX, int positionY, TerminalConfiguration configuration) {
        this.name = name;
        this.hostname = hostname;
        this.ipAddress = ipAddress;
        this.positionX = positionX;
        this.positionY = positionY;
        this.configuration = configuration;
        this.sessions = new ArrayList<Session>();
    }

    /**
     * Setters & Getters
     */
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

    /**
     * The method that registers a session
     * @param session to add
     */
    public boolean registerSession(Session session){
        return this.sessions.add(session);
    }

    /**
     * The method that returns all sessions associated with this terminal
     * @return a list of sessions
     */
    public ArrayList<Session> getSessions(){
        return sessions;
    }
}
