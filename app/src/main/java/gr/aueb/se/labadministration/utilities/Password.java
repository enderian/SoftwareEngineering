package gr.aueb.se.labadministration.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    private Password() {
    }

    public static String hash(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("unable to hash password", e);
        }

        md.update(password.getBytes());
        byte[] messageDigest = md.digest();
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < messageDigest.length; i++)
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        return hexString.toString().toUpperCase();
    }

}
