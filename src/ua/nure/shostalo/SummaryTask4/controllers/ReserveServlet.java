package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import ua.nure.shostalo.SummaryTask4.entity.Reserve;
import ua.nure.shostalo.SummaryTask4.entity.Room;
import ua.nure.shostalo.SummaryTask4.entity.StatusRoom;
import ua.nure.shostalo.SummaryTask4.entity.User;
import ua.nure.shostalo.SummaryTask4.services.AnswerService;
import ua.nure.shostalo.SummaryTask4.services.ReserveService;
import ua.nure.shostalo.SummaryTask4.services.RoomService;

/**
 * Servlet implementation class ReserveServlet.
 */
@WebServlet("/reserve")
public class ReserveServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ReserveServlet.class);

    /**
     * The service for rooms.
     */
    private RoomService roomService;

    /**
     * The service for reserves.
     */
    private ReserveService reserveService;

    /**
     * The service for answers.
     */
    private AnswerService answerService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveServlet() {
	LOGGER.info("constructor - ReserveServlet");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	roomService = (RoomService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_ROOM_SERVICE);
	reserveService = (ReserveService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_RESERVE_SERVICE);
	answerService = (AnswerService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_ANSWER_SERVICE);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	String locale = (String) session
		.getAttribute(Constants.ATTRIBUTE_LOCALE);
	Integer idRoom = Integer
		.parseInt(request.getParameter(Constants.PARAMETR_ROOM_ID));
	Room room = roomService.getRoomById(idRoom, Language.fromValue(locale));
	User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
	String arrival = request.getParameter(Constants.PARAMETR_ARRIVAL);
	String depature = request.getParameter(Constants.PARAMETR_DEPATURE);
	Date nowDate = new Date();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Reserve reserve = new Reserve(room, format.format(nowDate), arrival,
		depature, Constants.PARAMETR_STATUS_RESERVE,
		DateManagement.getPrice(arrival, depature,
			room.getClassRoom().getPrice()));
	reserveService.add(reserve, user.getLogin());
	roomService.updateStatus(room,
		StatusRoom.getValue(StatusRoom.RESERVED));
	if (request.getParameter(Constants.PARAMETR_ANSWER_ID) != null) {
	    Integer idAnswer = Integer.parseInt(
		    request.getParameter(Constants.PARAMETR_ANSWER_ID));
	    answerService.delete(idAnswer);
	    session.setAttribute(Constants.ATTRIBUTE_ANSWER_COUNT, answerService
		    .getAnswers(user.getLogin(), Language.fromValue(locale))
		    .size());
	}
	request.getRequestDispatcher("/items").forward(request, response);
    }

}
