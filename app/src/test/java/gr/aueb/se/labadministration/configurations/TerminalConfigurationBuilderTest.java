package gr.aueb.se.labadministration.configurations;

import gr.aueb.se.labadministration.builder.TerminalConfigurationBuilder;
import org.junit.Test;

public class TerminalConfigurationBuilderTest {

    @Test
    public void TerminalConfigurationBuilderTest(){
        TerminalConfiguration terminalConfiguration = new TerminalConfigurationBuilder()
                .setProcessor("i7")
                .setGraphicsCard("1070")
                .setstorageCapacity(1024)
                .setTotalMemory(16384)
                .setOperatingSystem("DB")
                .setName("UIID")
                .createTerminalConfiguration();
    }

}