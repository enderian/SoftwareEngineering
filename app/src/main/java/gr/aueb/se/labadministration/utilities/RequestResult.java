package gr.aueb.se.labadministration.utilities;

public class RequestResult {

    private boolean success;
    private String reasonOfFailure;

    public RequestResult(boolean success, String reasonOfFailure) {
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
