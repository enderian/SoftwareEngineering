package gr.aueb.se.labadministration.memorydao;

import gr.aueb.se.labadministration.dao.TerminalDAO;
import gr.aueb.se.labadministration.lab.Terminal;

import java.net.InetAddress;
import java.util.ArrayList;

public class TerminalDAOMemory implements TerminalDAO {

    protected static ArrayList<Terminal> terminals = new ArrayList<Terminal>();

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
