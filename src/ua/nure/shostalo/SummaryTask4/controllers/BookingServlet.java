package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.Room;
import ua.nure.shostalo.SummaryTask4.services.RoomService;

/**
 * Servlet implementation class BookingServlet.
 */
@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(BookingServlet.class);

    /**
     * The service for rooms.
     */
    private RoomService roomService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
	LOGGER.info("constructor - BookingServlet");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	roomService = (RoomService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_ROOM_SERVICE);
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
	Integer idRoom = Integer
		.parseInt(request.getParameter(Constants.PARAMETR_ROOM_ID));
	Room room = roomService.getRoomById(idRoom,
		Language.fromValue(language));
	request.setAttribute(Constants.ATTRIBUTE_ROOM, room);
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "reserveNow.jsp");
	request.getRequestDispatcher("views/items.jsp").forward(request,
		response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	response.sendRedirect("booking");
    }

}
