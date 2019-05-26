package gr.aueb.se.labadministration.utilities;

public class RequestResult {

    private boolean success, administrator;
    private String reasonOfFailure;

    public RequestResult(boolean success, boolean administrator, String reasonOfFailure) {
        this.success = success;
        this.administrator = administrator;
        this.reasonOfFailure = reasonOfFailure;
    }

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
