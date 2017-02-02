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
 * Servlet implementation class RegistrationServlet.
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger
	    .getLogger(RegistrationServlet.class);

    /**
     * The user service.
     */
    private UserService userService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
	LOGGER.info("constructor - RegistrationServlet");
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
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "registration.jsp");
	request.getRequestDispatcher("views/items.jsp").forward(request,
		response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding(Constants.ENCODING);
	String lastName = request.getParameter(Constants.PARAMETR_LAST_NAME);
	String name = request.getParameter(Constants.PARAMETR_NAME);
	String login = request.getParameter(Constants.PARAMETR_LOGIN);
	String password = request.getParameter(Constants.PARAMETR_PASSWORD);
	String email = request.getParameter(Constants.PARAMETR_EMAIL);
	String mobilePhone = request.getParameter(Constants.PARAMETR_PHONE);
	User user = new User(name, lastName, login, password, email,
		mobilePhone, Constants.PARAMETR_USER_ROLE);
	userService.add(user);
	HttpSession session = request.getSession();
	session.setAttribute(Constants.ATTRIBUTE_USER, user);
	request.getRequestDispatcher("/items").forward(request, response);
    }

}
