package gr.aueb.se.labadministration.stubs;

import gr.aueb.se.labadministration.interfaces.Presenter;
import gr.aueb.se.labadministration.interfaces.SignInActivity;
import gr.aueb.se.labadministration.presenter.SignInPresenter;
import gr.aueb.se.labadministration.utilities.RequestResult;

public class SignInActivityStub implements SignInActivity {

    private boolean opened;
    private SignInPresenter presenter;
    private boolean success = true;
    String username, password;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void showFailure() {
        success = false;
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

    public void setPassword(String password){
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public boolean isSuccess(){
        return  success;
    }
}
