package gr.aueb.se.labadministration.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.utilities.RequestResult;

/**
 * The class that communicates with all the activities & fragments regarding sign in operation
 */
public class SignInService extends Service {

    /**
     * Binder definition
     */
    public class SignInBinder extends Binder {
        public SignInService getService() {
            return SignInService.this;
        }
    }

    private UserDAO userDAO;

    /**
     * DAO Memory initialization
     */
    public SignInService() {
        userDAO = new UserDAOMemory();
    }

    /**
     * @return the DAO
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * Method that returns the result of a sign in request
     * @param username of user
     * @param password of user
     * @return the result of sign in action
     */
    public RequestResult signInRequest(String username, String password) {
        User user = getUserDAO().find(username);
        if (user == null) return new RequestResult(false, false, "User not found");
        return user.signIn(password);
    }

    /**
     * Default Android Methods
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new SignInBinder();
    }
}
