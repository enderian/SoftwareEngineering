package gr.aueb.se.labadministration.services;
public interface SignInService {

    SignInResult execute(String username, String password);

}
