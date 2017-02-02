package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class EnglishLocaleServlet.
 */
@WebServlet("/english")
public class EnglishLocaleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger
	    .getLogger(EnglishLocaleServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnglishLocaleServlet() {
	LOGGER.info("constructor - EnglishLocaleServlet");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	session.setAttribute(Constants.ATTRIBUTE_LOCALE, "en_US");
	response.sendRedirect("items");
    }

}
