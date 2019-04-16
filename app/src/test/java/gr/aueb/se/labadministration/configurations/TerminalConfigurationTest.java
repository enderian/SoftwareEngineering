package gr.aueb.se.labadministration.configurations;

import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalConfigurationTest {

    @Test
    public void TerminalConfigurationTest(){
        TerminalConfiguration terminalConfiguration = new TerminalConfiguration("i7", "1070", 1024, 16384, "Dual Boot", "UIID-11WU");
    }

}