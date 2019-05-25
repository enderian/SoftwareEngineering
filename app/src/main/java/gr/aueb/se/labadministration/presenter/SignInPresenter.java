package gr.aueb.se.labadministration.presenter;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.interfaces.Presenter;
import gr.aueb.se.labadministration.interfaces.SignInActivityInterface;
import gr.aueb.se.labadministration.services.SignIn;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignInPresenter implements Presenter {

    private SignInActivityInterface signInActivity; // ViewInterface Object
    private SignIn signIn; // Model Object
    private RequestResult result = null;

    public SignInPresenter(SignInActivityInterface signInActivity) {
        this.signInActivity = signInActivity;
        this.signIn = new SignIn();
    }

    public RequestResult performSignIn(){
        try {
            String username = signInActivity.getUsername();
            String password = signInActivity.getPassword();
            result =  signIn.signInRequest(username, password);
            if(!result.isSuccessful()){
                signInActivity.showFailure();
            }else{
                signInActivity.close();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void start(){
        signInActivity.setPresenter(this);
    }
}
