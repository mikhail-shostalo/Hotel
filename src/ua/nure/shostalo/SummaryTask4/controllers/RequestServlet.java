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
import ua.nure.shostalo.SummaryTask4.entity.Request;
import ua.nure.shostalo.SummaryTask4.entity.User;
import ua.nure.shostalo.SummaryTask4.services.RequestService;
import ua.nure.shostalo.SummaryTask4.services.RoomService;

/**
 * Servlet implementation class RequestServlet.
 */
@WebServlet("/request")
public class RequestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RequestServlet.class);

    /**
     * The service for rooms.
     */
    private RoomService roomService;

    /**
     * The request service.
     */
    private RequestService requestService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestServlet() {
	LOGGER.info("constructor - RequestServlet");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	roomService = (RoomService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_ROOM_SERVICE);
	requestService = (RequestService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_REQUEST_SERVICE);
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
	List<ClassRoom> rooms = roomService
		.getAllRoomClasses(Language.fromValue(language));
	request.setAttribute(Constants.ATTRIBUTE_ROOM_CLASSES, rooms);
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "request.jsp");
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
	User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
	Integer idClassRoom = Integer.parseInt(
		request.getParameter(Constants.PARAMETR_REQUEST_CLASS));
	Integer numberOfPeople = Integer.parseInt(
		request.getParameter(Constants.PARAMETR_REQUEST_NUMBER_PEOPLE));
	ClassRoom classRoom = roomService.getRoomClassById(idClassRoom);
	String arrivDate = request
		.getParameter(Constants.PARAMETR_REQUEST_ARRIVAL);
	String depDate = request.getParameter(Constants.PARAMETR_REQUEST_DEP);
	Request newReqest = new Request(classRoom, user.getLogin(),
		numberOfPeople, arrivDate, depDate);
	requestService.add(newReqest, user.getLogin());
	response.sendRedirect("request?status=1");
    }

}
