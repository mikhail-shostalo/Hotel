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

import ua.nure.shostalo.SummaryTask4.entity.DateManagement;
import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.Request;
import ua.nure.shostalo.SummaryTask4.entity.Room;
import ua.nure.shostalo.SummaryTask4.services.AnswerService;
import ua.nure.shostalo.SummaryTask4.services.RequestService;
import ua.nure.shostalo.SummaryTask4.services.RoomService;

/**
 * Servlet implementation class RoomManagementServlet.
 */
@WebServlet("/roomManagment")
public class RoomManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger
	    .getLogger(RoomManagementServlet.class);

    /**
     * The requestService.
     */
    private RequestService requestService;

    /**
     * The service for rooms.
     */
    private RoomService roomService;

    /**
     * The service for answers.
     */
    private AnswerService answerService;

    /**
     * The request id.
     */
    private Integer idRequest;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomManagementServlet() {
	LOGGER.info("constructor - RoomManagementServlet");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	requestService = (RequestService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_REQUEST_SERVICE);
	roomService = (RoomService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_ROOM_SERVICE);
	answerService = (AnswerService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_ANSWER_SERVICE);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	idRequest = Integer
		.parseInt(request.getParameter(Constants.PARAMETR_REQUEST_ID));
	String login = request.getParameter(Constants.PARAMETR_LOGIN);
	request.setAttribute(Constants.ATTRIBUTE_LOGIN, login);
	Request req = requestService.getById(idRequest);
	request.setAttribute(Constants.ATTRIBUTE_ARRIVAL, req.getArrivalDate());
	request.setAttribute(Constants.ATTRIBUTE_DEPATURE,
		req.getDateOfDeparture());
	int days = DateManagement.getDays(req.getArrivalDate(),
		req.getDateOfDeparture());
	String locale = (String) session
		.getAttribute(Constants.ATTRIBUTE_LOCALE);
	List<Room> roomList = roomService.getRoomsByNumberOfPeople(
		req.getNumberOfPlace(), Language.fromValue(locale));
	request.setAttribute(Constants.ATTRIBUTE_ROOMS, roomList);
	request.setAttribute(Constants.ATTRIBUTE_DAYS, days);
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "tableMangment.jsp");
	request.getRequestDispatcher("views/items.jsp").forward(request,
		response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	Integer id = Integer
		.parseInt(request.getParameter(Constants.PARAMETR_ROOM_ID));
	String login = request.getParameter(Constants.PARAMETR_LOGIN);
	Double price = Double.parseDouble(
		request.getParameter(Constants.PARAMETR_ROOM_PRICE));
	String arrival = request.getParameter(Constants.PARAMETR_ARRIVAL);
	String depature = request.getParameter(Constants.PARAMETR_DEPATURE);
	answerService.add(id, login, price, arrival, depature);
	requestService.delete(idRequest);
	session.setAttribute(Constants.ATTRIBUTE_REQUEST_COUNT,
		requestService.getRequests().size());
	response.sendRedirect("requestList");
    }

}
