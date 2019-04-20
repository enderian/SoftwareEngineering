package gr.aueb.se.labadministration.interfaces;

import gr.aueb.se.labadministration.presenter.SignInPresenter;
import gr.aueb.se.labadministration.utilities.RequestResult;

public interface SignInFragment {

    boolean getSuccess();
    String getFail();
    String getReasonOfFailure();
    void performSignIn(String username, String password);
    void show();
    void hide();

    void setSignInPresenter(SignInPresenter presenter);
}
