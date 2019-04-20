package gr.aueb.se.labadministration.services;

public class SignInResult {

    private boolean success;
    private String reasonOfFailure;

    public SignInResult(boolean success, String reasonOfFailure) {
        this.success = success;
        this.reasonOfFailure = reasonOfFailure;
    }

    public boolean isSuccessful() {
        return success;
    }

    public String getReasonOfFailure() {
        return reasonOfFailure;
    }
}
