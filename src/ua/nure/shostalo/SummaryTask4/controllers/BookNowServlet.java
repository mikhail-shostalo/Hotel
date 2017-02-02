package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
import ua.nure.shostalo.SummaryTask4.util.SortEnum;
import ua.nure.shostalo.SummaryTask4.util.Sorter;

/**
 * Servlet implementation class BookNowServlet.
 */
@WebServlet("/bookNow")
public class BookNowServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(BookNowServlet.class);

    /**
     * The service for rooms.
     */
    private RoomService roomService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookNowServlet() {
	LOGGER.info("constructor - BookNowServlet");
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
	String locale = (String) session
		.getAttribute(Constants.ATTRIBUTE_LOCALE);
	List<Room> roomList = roomService
		.getAllRooms(Language.fromValue(locale));
	String sortParametr = request.getParameter(Constants.PARAMETR_SORT);
	if (sortParametr != null) {
	    switch (SortEnum.fromValue(sortParametr)) {
	    case PRICE:
		Collections.sort(roomList, Sorter.SORT_ROOM_BY_PRICE);
		break;
	    case CLASS:
		Collections.sort(roomList, Sorter.SORT_ROOM_BY_CLASS);
		break;
	    case STATUS:
		Collections.sort(roomList, Sorter.SORT_ROOM_BY_STATUS);
	    default:
		Collections.sort(roomList, Sorter.SORT_ROOM_BY_PLACES);
		break;
	    }
	}
	request.setAttribute(Constants.ATTRIBUTE_ROOMS, roomList);
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "tableOfRooms.jsp");
	request.getRequestDispatcher("views/items.jsp").forward(request,
		response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
    }

}
