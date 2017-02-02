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

import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.Reserve;
import ua.nure.shostalo.SummaryTask4.entity.ReserveStatus;
import ua.nure.shostalo.SummaryTask4.entity.User;
import ua.nure.shostalo.SummaryTask4.services.ReserveService;

/**
 * Servlet implementation class RoomsList.
 */
@WebServlet("/myRooms")
public class RoomsList extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RoomsList.class);

    /**
     * The service for reserves.
     */
    private ReserveService reserveService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomsList() {
	LOGGER.info("constructor - RoomsList");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
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
	User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
	String locale = (String) session
		.getAttribute(Constants.ATTRIBUTE_LOCALE);
	List<Reserve> list = reserveService.getReserves(user.getLogin(),
		ReserveStatus.getValue(ReserveStatus.PAID),
		Language.fromValue(locale));
	request.setAttribute(Constants.ATTRIBUTE_RESERVES, list);
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "myRooms.jsp");
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
