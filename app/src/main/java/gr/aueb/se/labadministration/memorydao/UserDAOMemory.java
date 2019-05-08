package gr.aueb.se.labadministration.memorydao;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.people.Administrator;
import gr.aueb.se.labadministration.domain.people.User;

import java.util.ArrayList;

public class UserDAOMemory implements UserDAO {

    protected static ArrayList<User> users = new ArrayList<User>();

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public User find(String username) {
        for(User user : users){
            if(user.getUsername().equals(username)) return user;
        }
        return null;
    }

    @Override
    public ArrayList<Session> listAllSessions(User user) {
        for(User u : users){
            if(u.equals(user)) return u.listSessions();
        }
        return null;
    }

    @Override
    public ArrayList<Administrator> listAllAdministrators(){
        ArrayList<Administrator> administrators = new ArrayList<>();
        for(User u : users){
            if (u instanceof Administrator) administrators.add((Administrator)u);
        }
        return administrators;
    }
}
