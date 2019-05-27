package gr.aueb.se.labadministration.utilities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Getters Tests
 */
public class RequestResultTest {

    private RequestResult requestResult;

    @Before
    public void initialize(){
        this.requestResult = new RequestResult(true,true, "Success");
    }

    @Test
    public void isSuccess() {
        Assert.assertTrue(this.requestResult.isSuccessful());
    }

    @Test
    public void getReasonOfFailure() {
        Assert.assertNotNull(this.requestResult.getReasonOfFailure());
    }

    @Test
    public void isAdministrator() {
        Assert.assertTrue(this.requestResult.isAdministrator());
    }
}