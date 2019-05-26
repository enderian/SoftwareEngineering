package gr.aueb.se.labadministration.services;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.se.labadministration.domain.lab.Session;

import static org.junit.Assert.*;

public class HistoryServiceTest {

    private static HistoryService historyService;

    @BeforeClass
    public static void setup(){
        historyService = new HistoryServiceStub();
    }

    @Test
    public void getAllSessions(){
        List<Session> sessions = historyService.allSessions();

        Assert.assertTrue(!sessions.isEmpty());
    }

    @Test
    public void listSessionsOfUser(){
        List<Session> sessions = historyService.findSessionsByUser("d2");

        Assert.assertTrue(!sessions.isEmpty());
    }

    @Test
    public void listSessionsOfTerminal(){
        List<Session> sessions = historyService.findSessionsByComputer("T");

        Assert.assertTrue(!sessions.isEmpty());
    }
}