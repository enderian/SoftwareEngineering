package gr.aueb.se.labadministration.stubs;

import java.security.NoSuchAlgorithmException;

import gr.aueb.se.labadministration.interfaces.SignInFragment;
import gr.aueb.se.labadministration.presenter.SignInPresenter;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignInFragmentStub implements SignInFragment {

    private boolean opened;
    private SignInPresenter presenter;
    private String fail;
    private String reasonOfFail;
    private boolean success;

    @Override
    public boolean getSuccess() {
        return success;
    }

    @Override
    public String getFail() {
        return fail;
    }

    @Override
    public String getReasonOfFailure() {
        return reasonOfFail;
    }

    @Override
    public void performSignIn(String username, String password) {
        RequestResult result = presenter.performSignIn(username, password);
        success = result.isSuccessful();
        if(!result.isSuccessful()){
            fail = "Failed to login";
            reasonOfFail = result.getReasonOfFailure();
        }
    }

    @Override
    public void show() {
        this.opened = true;
    }

    @Override
    public void hide() {
        this.opened = false;
    }

    @Override
    public void setSignInPresenter(SignInPresenter presenter) {
        this.presenter = presenter;
    }

    public boolean isOpened() {
        return opened;
    }
}
