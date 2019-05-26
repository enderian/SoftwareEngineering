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

public class SignInService extends Service {

    public class SignInBinder extends Binder {
        public SignInService getService() {
            return SignInService.this;
        }
    }

    private UserDAO userDAO;

    public SignInService() {
        userDAO = new UserDAOMemory();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public RequestResult signInRequest(String username, String password) {
        User user = getUserDAO().find(username);
        if (user == null) return new RequestResult(false, false, "User not found");
        return user.signIn(password);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new SignInBinder();
    }
}
