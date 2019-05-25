package gr.aueb.se.labadministration.interfaces;

import gr.aueb.se.labadministration.presenter.SignInPresenter;
import gr.aueb.se.labadministration.utilities.RequestResult;

public interface SignInActivity extends View{

    String getPassword();
    String getUsername();
    void showFailure();

}
