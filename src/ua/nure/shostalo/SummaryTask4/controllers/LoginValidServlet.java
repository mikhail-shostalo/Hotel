package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.shostalo.SummaryTask4.services.UserService;

/**
 * Servlet implementation class LoginValidServlet.
 */
@WebServlet("/loginExist")
public class LoginValidServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger
	    .getLogger(LoginValidServlet.class);

    /**
     * The user service.
     */
    private UserService userService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidServlet() {
	LOGGER.info("constructor - LoginValidServlet");
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
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String login = request.getParameter("login");
	Matcher matcher = Pattern.compile("^[A-Za-z0-9_]+").matcher(login);
	Boolean result = null;
	if (matcher.matches()) {
	    result = userService.isLoginUsed(login);
	} else {
	    result = true;
	}
	PrintWriter out = response.getWriter();
	if (!result) {
	    out.print("OK");
	} else {
	    out.print("Error");
	}
	out.flush();
	out.close();
    }

}
