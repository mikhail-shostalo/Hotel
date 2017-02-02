package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.shostalo.SummaryTask4.entity.ClassRoom;
import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.ReserveStatus;
import ua.nure.shostalo.SummaryTask4.entity.User;
import ua.nure.shostalo.SummaryTask4.services.AnswerService;
import ua.nure.shostalo.SummaryTask4.services.RequestService;
import ua.nure.shostalo.SummaryTask4.services.ReserveService;
import ua.nure.shostalo.SummaryTask4.services.RoomService;

/**
 * Servlet implementation class ItemsServlet.
 */
@WebServlet("/items")
public class ItemsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemsServlet.class);

    /**
     * The service for rooms.
     */
    private RoomService roomService;

    /**
     * The service for requests.
     */
    private RequestService requestService;

    /**
     * The service for answers.
     */
    private AnswerService answerService;

    /**
     * The service for reserves.
     */
    private ReserveService reserveService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsServlet() {
	LOGGER.info("constructor - ItemsServlet");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	roomService = (RoomService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_ROOM_SERVICE);
	requestService = (RequestService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_REQUEST_SERVICE);
	answerService = (AnswerService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_ANSWER_SERVICE);
	reserveService = (ReserveService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_RESERVE_SERVICE);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	String language = (String) session
		.getAttribute(Constants.ATTRIBUTE_LOCALE);
	session.setAttribute(Constants.ATTRIBUTE_REQUEST_COUNT,
		requestService.getRequests().size());
	User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
	if (user != null) {
	    session.setAttribute(Constants.ATTRIBUTE_ANSWER_COUNT, answerService
		    .getAnswers(user.getLogin(), Language.fromValue(language))
		    .size());
	    session.setAttribute(Constants.ATTRIBUTE_RESERVE_COUNT,
		    reserveService.getReserves(user.getLogin(),
			    ReserveStatus.getValue(ReserveStatus.BOOKED),
			    Language.fromValue(language)).size());
	}
	List<ClassRoom> rooms = roomService
		.getAllRoomClasses(Language.fromValue(language));
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "rooms.jsp");
	request.setAttribute(Constants.ATTRIBUTE_ROOM_CLASSES, rooms);
	request.setAttribute(Constants.ATTRIBUTE_BLOCK, "block.jsp");
	request.getRequestDispatcher("views/items.jsp").forward(request,
		response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	doGet(req, resp);
    }

}