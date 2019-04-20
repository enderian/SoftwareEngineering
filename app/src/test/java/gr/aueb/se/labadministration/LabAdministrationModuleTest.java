package gr.aueb.se.labadministration;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.junit.Assert;
import org.junit.Test;

public class LabAdministrationModuleTest {

    @Test
    public void initializeInjector() {
        Injector injector = Guice.createInjector(new LabAdministrationModule());
        Assert.assertNotNull(injector);
    }

}
