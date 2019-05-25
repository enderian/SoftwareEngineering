package gr.aueb.se.labadministration.presenter;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.interfaces.Presenter;
import gr.aueb.se.labadministration.interfaces.SignInActivity;
import gr.aueb.se.labadministration.services.SignIn;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignInPresenter implements Presenter {

    private SignInActivity signInActivity;
    private SignIn signIn;
    private RequestResult result = null;

    public SignInPresenter(SignInActivity signInActivity) {
        this.signInActivity = signInActivity;
        this.signIn = new SignIn();
    }

    public RequestResult performSignIn(String username, String password){
        try {
            return signIn.signInRequest(username, password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void start(){
        signInActivity.setPresenter(this);
        signInActivity.open();
    }
}
