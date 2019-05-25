package gr.aueb.se.labadministration.stubs;

import gr.aueb.se.labadministration.interfaces.Presenter;
import gr.aueb.se.labadministration.interfaces.SignInActivityInterface;
import gr.aueb.se.labadministration.presenter.SignInPresenter;

public class SignInActivityStub implements SignInActivityInterface {

    private boolean closed;
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
    public void setPresenter(Presenter presenter) {
        this.presenter = (SignInPresenter) presenter;
    }

    @Override
    public void close(){
        closed = true;
    }

    public boolean isClosed() {
        return closed;
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
