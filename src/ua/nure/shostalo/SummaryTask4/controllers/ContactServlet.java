package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ContactServlet.
 */
@WebServlet("/contact")
public class ContactServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ContactServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
	LOGGER.info("constructor - ContactServlet");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "contact.jsp");
	request.getRequestDispatcher("views/items.jsp").forward(request,
		response);
    }

}
