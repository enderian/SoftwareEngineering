package gr.aueb.se.labadministration.services;

import java.security.NoSuchAlgorithmException;

public interface SignInService {

    SignInResult execute(String username, String password) throws NoSuchAlgorithmException;

}
