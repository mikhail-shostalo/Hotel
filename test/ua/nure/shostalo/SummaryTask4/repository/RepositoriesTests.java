package ua.nure.shostalo.SummaryTask4.repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import ua.nure.shostalo.SummaryTask4.controllers.Constants;
import ua.nure.shostalo.SummaryTask4.repository.exception.RepositoryException;
import ua.nure.shostalo.SummaryTask4.services.AnswerService;
import ua.nure.shostalo.SummaryTask4.services.RequestService;
import ua.nure.shostalo.SummaryTask4.services.RoomService;
import ua.nure.shostalo.SummaryTask4.services.TransactionManager;
import ua.nure.shostalo.SummaryTask4.services.UserService;

public class RepositoriesTests {

    private AnswerService answer;

    private RequestService request;

    private RoomService room;

    private UserService user;

    @Before
    public void initTest() {
	AnswerRepository answerRep = new AnswerRepository();
	RequestRepository requestRep = new RequestRepository();
	RoomRepository roomRep = new RoomRepository();
	UserRepository userRep = new UserRepository();
	DataSource ds = null;
	try {
	    Context initContext = new InitialContext();
	    ds = (DataSource) initContext.lookup(Constants.DATA_SOURCE_CONTEXT);
	} catch (NamingException e) {
	    throw new RepositoryException();
	}
	TransactionManager tm = new TransactionManager(ds);
	answer = new AnswerService(tm, answerRep);
	request = new RequestService(tm, requestRep);
	room = new RoomService(tm, roomRep);
	user = new UserService(tm, userRep);
    }

    @Test
    public void answerRepository() {
	answer.add(1, "test", 1.0, "2017-01-01", "2017-01-02");
    }
}
