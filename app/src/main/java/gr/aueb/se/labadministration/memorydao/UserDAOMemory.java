package gr.aueb.se.labadministration.memorydao;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.people.Administrator;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.utilities.Password;

import java.util.ArrayList;

/**
 * The interface the communicates with memory for the users
 */
public class UserDAOMemory implements UserDAO {

    protected static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User("d2111111", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student"));
        users.add(new User("p3160026", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student"));
        users.add(new User("teacher", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "teacher"));
    }

    /**
     * The method that saves users to the memory
     * @param user
     */
    @Override
    public void save(User user) {
        users.add(user);
    }

    /**
     * The method that removes users from the memory
     * @param user
     */
    @Override
    public void delete(User user) {
        users.remove(user);
    }

    /**
     * The method that finds users by their username
     * @param username
     * @return user or null
     */
    @Override
    public User find(String username) {
        for(User user : users){
            if(user.getUsername().equals(username)) return user;
        }
        return null;
    }

    /**
     * The method that returns all users
     * @return
     */
    @Override
    public ArrayList<User> listUsers() {
        return users;
    }

    /**
     * The method that returns the data from which terminals have been used by the user
     * @param user
     * @return user or null
     */
    @Override
    public ArrayList<Session> listAllSessions(User user) {
        for(User u : users){
            if(u.equals(user)) return u.listSessions();
        }
        return null;
    }

    /**
     * The method that returns all the administrators
     * @return all users that are admins
     */
    @Override
    public ArrayList<Administrator> listAllAdministrators(){
        ArrayList<Administrator> administrators = new ArrayList<>();
        for(User u : users){
            if (u instanceof Administrator) administrators.add((Administrator)u);
        }
        return administrators;
    }
}