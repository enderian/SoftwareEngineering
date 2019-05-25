package gr.aueb.se.labadministration.interfaces;


public interface SignInActivityInterface extends ViewInterface {

    String getPassword();
    String getUsername();
    void showFailure();

}
