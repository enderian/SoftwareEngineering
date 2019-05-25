package gr.aueb.se.labadministration.interfaces;


public interface SignInActivity extends View{

    String getPassword();
    String getUsername();
    void showFailure();

}
