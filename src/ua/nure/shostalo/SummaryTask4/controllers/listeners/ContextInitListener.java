package ua.nure.shostalo.SummaryTask4.controllers.listeners;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.nure.shostalo.SummaryTask4.controllers.Constants;
import ua.nure.shostalo.SummaryTask4.repository.AnswerRepository;
import ua.nure.shostalo.SummaryTask4.repository.RequestRepository;
import ua.nure.shostalo.SummaryTask4.repository.ReserveRepository;
import ua.nure.shostalo.SummaryTask4.repository.RoomRepository;
import ua.nure.shostalo.SummaryTask4.repository.UserRepository;
import ua.nure.shostalo.SummaryTask4.repository.exception.RepositoryException;
import ua.nure.shostalo.SummaryTask4.services.AnswerService;
import ua.nure.shostalo.SummaryTask4.services.RequestService;
import ua.nure.shostalo.SummaryTask4.services.ReserveService;
import ua.nure.shostalo.SummaryTask4.services.RoomService;
import ua.nure.shostalo.SummaryTask4.services.TransactionManager;
import ua.nure.shostalo.SummaryTask4.services.UserService;

/**
 * Application Lifecycle Listener implementation class
 * ContextInitListener.
 *
 */
@WebListener
public class ContextInitListener implements ServletContextListener {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger
	    .getLogger(ContextInitListener.class);

    @Override
    public void contextDestroyed(final ServletContextEvent arg0) {
	LOGGER.info("contextDestroyed - ContextInitListener");
    }

    @Override
    public final void contextInitialized(final ServletContextEvent arg0) {
	DataSource ds = null;
	PropertyConfigurator.configure(arg0.getServletContext()
		.getRealPath(Constants.PATH_TO_LOG4J_CONFIG));
	LOGGER.info("contextInitialized");
	try {
	    Context initContext = new InitialContext();
	    ds = (DataSource) initContext.lookup(Constants.DATA_SOURCE_CONTEXT);
	} catch (NamingException e) {
	    throw new RepositoryException();
	}
	TransactionManager tm = new TransactionManager(ds);
	RoomRepository roomRepository = new RoomRepository();
	RoomService roomService = new RoomService(tm, roomRepository);
	arg0.getServletContext().setAttribute(Constants.ATTRIBUTE_ROOM_SERVICE,
		roomService);
	UserRepository userRepository = new UserRepository();
	UserService userService = new UserService(tm, userRepository);
	arg0.getServletContext().setAttribute(Constants.ATTRIBUTE_USER_SERVICE,
		userService);
	RequestRepository requestRepository = new RequestRepository();
	RequestService requestService = new RequestService(tm,
		requestRepository);
	arg0.getServletContext().setAttribute(
		Constants.ATTRIBUTE_REQUEST_SERVICE, requestService);
	AnswerRepository answerRepository = new AnswerRepository();
	AnswerService answerService = new AnswerService(tm, answerRepository);
	arg0.getServletContext().setAttribute(
		Constants.ATTRIBUTE_ANSWER_SERVICE, answerService);
	ReserveRepository reserveRepository = new ReserveRepository();
	ReserveService reserveService = new ReserveService(tm,
		reserveRepository);
	arg0.getServletContext().setAttribute(
		Constants.ATTRIBUTE_RESERVE_SERVICE, reserveService);
    }

}
