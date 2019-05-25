package gr.aueb.se.labadministration.stubs;

import gr.aueb.se.labadministration.interfaces.Presenter;
import gr.aueb.se.labadministration.interfaces.SignInActivity;
import gr.aueb.se.labadministration.presenter.SignInPresenter;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignInActivityStub implements SignInActivity {

    private boolean opened;
    private SignInPresenter presenter;
    private String failureMessage;
    private boolean getSuccess;

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public void showFailure() {
        failureMessage = "username or/and password are wrong.";
    }

    @Override
    public void open() {
        this.opened = true;
    }

    @Override
    public void close() {
        this.opened = false;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = (SignInPresenter) presenter;
    }


    public boolean isOpened() {
        return opened;
    }
}
