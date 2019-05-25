package gr.aueb.se.labadministration.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;

import gr.aueb.se.labadministration.activities.HistoryActivity;
import gr.aueb.se.labadministration.dao.UserDAO;
import gr.aueb.se.labadministration.domain.builder.TerminalBuilder;
import gr.aueb.se.labadministration.domain.builder.TerminalConfigurationBuilder;
import gr.aueb.se.labadministration.domain.configurations.TerminalConfiguration;
import gr.aueb.se.labadministration.domain.lab.Session;
import gr.aueb.se.labadministration.domain.lab.Terminal;
import gr.aueb.se.labadministration.domain.people.User;
import gr.aueb.se.labadministration.memorydao.TerminalDAOMemory;
import gr.aueb.se.labadministration.memorydao.UserDAOMemory;
import gr.aueb.se.labadministration.stubs.HistoryActivityStub;

public class HistoryPresenterTest {

    private HistoryPresenter presenter;
    private HistoryActivityStub stub;
    private Terminal terminal;
    private User user;

    @Before
    public void initialize(){

        stub = new HistoryActivityStub();
        presenter = new HistoryPresenter(stub);

        TerminalConfiguration configuration = new TerminalConfigurationBuilder()
                .setName("T")
                .setOperatingSystem("OS")
                .setProcessor("i")
                .setstorageCapacity(2014)
                .setTotalMemory(1024)
                .createTerminalConfiguration();

        TerminalDAOMemory terminalDao = new TerminalDAOMemory();
        try{
            terminal = new TerminalBuilder()
                    .setConfiguration(configuration)
                    .setHostname("T")
                    .setIpAddress(InetAddress.getByName("127.0.0.1"))
                    .setName("T")
                    .setPositionX(0).setPositionY(0).createTerminal();
            terminalDao.save(terminal);
        }catch (Exception e){
            e.printStackTrace();
        }



        user = new User("p3160079", "5E884898DA2847151D0E56F8DC6292773603DD6AABBDD62A11EF721D1542D8", "student");
        UserDAOMemory userDao = new UserDAOMemory();
        userDao.save(user);


        Session session = new Session(terminal, user, Session.SessionStatus.FINISHED, new Date(), new Date());
        Session session1 = new Session(terminal, user, Session.SessionStatus.FINISHED, new Date(), new Date());
        Session session2 = new Session(terminal, user, Session.SessionStatus.FINISHED, new Date(), new Date());
        session.updateSessions();
        session1.updateSessions();
        session2.updateSessions();
    }

    @Test
    public void checkPresenter(){
        presenter.start();

        Assert.assertTrue(stub.getPresenter() == presenter);
    }

    @Test
    public void successfulSearchComputer(){

        Assert.assertTrue(stub.getNumberOfSessions()==0);

        stub.setId("T");
        stub.setOption("computer");
        presenter.searchEvent();

        Assert.assertTrue(stub.isResults());
        Assert.assertFalse(stub.isError());
    }

    @Test
    public void FailureSearch(){

        // empty id = failure
        stub.setOption("computer");
        presenter.searchEvent();

        Assert.assertFalse(stub.isResults());
        Assert.assertTrue(stub.isError());
    }

    @Test
    public void successfulSearchUser(){

        stub.setId("p3160079");
        stub.setOption("user");
        presenter.searchEvent();

        Assert.assertTrue(stub.isResults());
        Assert.assertFalse(stub.isError());
    }
}
