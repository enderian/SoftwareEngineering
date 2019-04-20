package gr.aueb.se.labadministration.presenter;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.interfaces.SignInFragment;
import gr.aueb.se.labadministration.services.SignIn;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignInPresenter {

    private SignInFragment signInFragment;
    private SignIn signIn;
    private RequestResult result = null;

    public SignInPresenter(SignInFragment signInFragment) {
        this.signInFragment = signInFragment;
        this.signIn = new SignIn();
    }

    public RequestResult performSignIn(String username, String password){
        try {
            return signIn.signInRequest(username, password, "student");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void start(){
        signInFragment.setSignInPresenter(this);
        signInFragment.show();
    }
}
