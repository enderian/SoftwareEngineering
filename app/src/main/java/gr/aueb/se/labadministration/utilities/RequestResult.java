package gr.aueb.se.labadministration.utilities;

/**
 * The class that provides the sign in result
 */
public class RequestResult {

    private boolean success, administrator;
    private String reasonOfFailure;

    /**
     * Constructor
     * @param success of sign in
     * @param administrator privileged user
     * @param reasonOfFailure of sign in
     */
    public RequestResult(boolean success, boolean administrator, String reasonOfFailure) {
        this.success = success;
        this.administrator = administrator;
        this.reasonOfFailure = reasonOfFailure;
    }

    /**
     * Getters
     */
    public boolean isSuccessful() {
        return success;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public String getReasonOfFailure() {
        return reasonOfFailure;
    }
}
