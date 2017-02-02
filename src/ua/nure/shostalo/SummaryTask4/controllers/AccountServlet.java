package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.Reserve;
import ua.nure.shostalo.SummaryTask4.entity.ReserveStatus;
import ua.nure.shostalo.SummaryTask4.entity.Room;
import ua.nure.shostalo.SummaryTask4.entity.StatusRoom;
import ua.nure.shostalo.SummaryTask4.entity.User;
import ua.nure.shostalo.SummaryTask4.services.ReserveService;
import ua.nure.shostalo.SummaryTask4.services.RoomService;

/**
 * Servlet implementation class AccountServlet.
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AccountServlet.class);

    /**
     * The service for reserves.
     */
    private ReserveService reserveService;

    /**
     * The service for rooms.
     */
    private RoomService roomService;

    private ServletContext context;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
	LOGGER.info("constructor - AccountServlet");
    }

    @Override
    public void init(final ServletConfig config) throws ServletException {
	reserveService = (ReserveService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_RESERVE_SERVICE);
	roomService = (RoomService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_ROOM_SERVICE);
	context = config.getServletContext();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(final HttpServletRequest request,
	    final HttpServletResponse response)
	    throws ServletException, IOException {

	HttpSession session = request.getSession();
	String language = (String) session
		.getAttribute(Constants.ATTRIBUTE_LOCALE);
	User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
	List<Reserve> reserves = reserveService.getReserves(user.getLogin(),
		ReserveStatus.getValue(ReserveStatus.BOOKED),
		Language.fromValue(language));
	request.setAttribute(Constants.ATTRIBUTE_RESERVES, reserves);
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "account.jsp");
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
	String language = (String) session
		.getAttribute(Constants.ATTRIBUTE_LOCALE);
	Integer roomId = Integer
		.parseInt(request.getParameter(Constants.PARAMETR_ROOM_ID));
	User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
	Room room = roomService.getRoomById(roomId,
		Language.fromValue(language));
	roomService.updateStatus(room,
		StatusRoom.getValue(StatusRoom.OCCUPIED));
	Integer idReserve = Integer
		.parseInt(request.getParameter(Constants.PARAMETR_RESERVE_ID));
	StringBuilder message = new StringBuilder();
	List<Reserve> list = reserveService.getReserves(user.getLogin(),
		ReserveStatus.getValue(ReserveStatus.BOOKED),
		Language.fromValue(language));
	for (Reserve item : list) {
	    if (item.getId() == idReserve) {
		message.append("Room class : ")
			.append(item.getRoom().getClassRoom().getRoomClass())
			.append("\nDates : ").append(item.getArrivalDate())
			.append(" - ").append(item.getDateOfDepartures())
			.append("\nPrice : ").append(item.getPrice());
	    }
	}
	reserveService.updateStatus(ReserveStatus.getValue(ReserveStatus.PAID),
		idReserve);
	list = reserveService.getReserves(user.getLogin(),
		ReserveStatus.getValue(ReserveStatus.BOOKED),
		Language.fromValue(language));
	Properties prop = new Properties();
	String fileName = context.getInitParameter("mail");

	prop.load(context.getResourceAsStream(fileName));
	MailThread mailThread = new MailThread(user.getEmail(),
		message.toString(), prop);
	mailThread.start();
	session.setAttribute(Constants.ATTRIBUTE_RESERVE_COUNT, list.size());
	response.sendRedirect("account");
    }

}
