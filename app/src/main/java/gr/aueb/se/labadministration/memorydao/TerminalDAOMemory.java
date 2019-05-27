package gr.aueb.se.labadministration.memorydao;

import android.content.ServiceConnection;

import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.lab.Terminal;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

public class TerminalDAOMemory implements TerminalDAO {

    protected static ArrayList<Terminal> terminals = new ArrayList<>();

    static {
        try {
            Terminal term1 = new TerminalBuilder()
                    .setHostname("cslab1-22")
                    .setName("CSLAB1-22")
                    .setPositionX(0)
                    .setPositionY(0)
                    .setIpAddress(InetAddress.getByName("172.16.1.22"))
                    .setConfiguration(TerminalConfigurationDAOMemory.configurations.get("CSLAB1"))
                    .createTerminal();
            Terminal term2 = new TerminalBuilder()
                    .setHostname("cslab1-23")
                    .setName("CSLAB1-23")
                    .setPositionX(1)
                    .setPositionY(0)
                    .setIpAddress(InetAddress.getByName("172.16.1.23"))
                    .setConfiguration(TerminalConfigurationDAOMemory.configurations.get("CSLAB1"))
                    .createTerminal();
            Terminal term3 = new TerminalBuilder()
                    .setHostname("cslab2-32")
                    .setName("CSLAB2-32")
                    .setPositionX(0)
                    .setPositionY(0)
                    .setIpAddress(InetAddress.getByName("172.16.2.32"))
                    .setConfiguration(TerminalConfigurationDAOMemory.configurations.get("CSLAB2"))
                    .createTerminal();
            Terminal term4 = new TerminalBuilder()
                    .setHostname("cslab2-33")
                    .setName("CSLAB2-33")
                    .setPositionX(1)
                    .setPositionY(0)
                    .setIpAddress(InetAddress.getByName("172.16.2.33"))
                    .setConfiguration(TerminalConfigurationDAOMemory.configurations.get("CSLAB2"))
                    .createTerminal();
            term1.setStatus(Terminal.TerminalStatus.AVAILABLE);
            term2.setStatus(Terminal.TerminalStatus.IN_MAINTENANCE);
            term3.setStatus(Terminal.TerminalStatus.IN_USE);
            term4.setStatus(Terminal.TerminalStatus.OFFLINE);
            terminals.add(term1);
            terminals.add(term2);
            terminals.add(term3);
            terminals.add(term4);
        } catch (UnknownHostException ignored) {}
    }

    static {
        // Register session.
        new Session(terminals.get(0), UserDAOMemory.users.get(0), Session.SessionStatus.STARTED, new Date(), null);
    }

    @Override
    public void save(Terminal terminal) {
        terminals.add(terminal);
    }

    @Override
    public void delete(Terminal terminal) {
        terminals.remove(terminal);
    }

    @Override
    public Terminal findByName(String terminalName) {
        for(Terminal terminal : terminals){
            if(terminal.getName().equalsIgnoreCase(terminalName)) return terminal;
        }
        return null;
    }

    @Override
    public Terminal findByIP(InetAddress inetAddress) {
        for(Terminal terminal : terminals){
            if(terminal.getIpAddress().equals(inetAddress)) return terminal;
        }
        return null;
    }

    @Override
    public void updateStatus(Terminal terminal, Terminal.TerminalStatus status){
        for(Terminal t : terminals){
            if(t.equals(terminal)) terminal.setStatus(status);
        }
    }

    @Override
    public ArrayList<Terminal> listAll() {
        return terminals;
    }
}
