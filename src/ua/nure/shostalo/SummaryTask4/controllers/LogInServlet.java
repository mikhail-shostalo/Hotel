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

import ua.nure.shostalo.SummaryTask4.entity.User;
import ua.nure.shostalo.SummaryTask4.services.UserService;

/**
 * Servlet implementation class LogInServlet.
 */
@WebServlet("/logIn")
public class LogInServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(LogInServlet.class);

    /**
     * The service for user.
     */
    private UserService userService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
	LOGGER.info("constructor - LogInServlet");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	userService = (UserService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_USER_SERVICE);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "logIn.jsp");
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
	String login = request.getParameter(Constants.PARAMETR_LOGIN);
	String password = request.getParameter(Constants.PARAMETR_PASSWORD);
	User user = userService.getUser(login, password);
	if (user != null) {
	    session.setAttribute(Constants.ATTRIBUTE_USER, user);
	    response.sendRedirect("items");
	} else {
	    response.sendRedirect("logIn?message=1");
	}
    }

}
