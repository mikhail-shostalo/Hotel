package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class AboutServlet.
 */
@WebServlet("/about")
public class AboutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AboutServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutServlet() {
	LOGGER.info("constructor AboutServlet");
    }

    @Override
    protected void doGet(final HttpServletRequest request,
	    final HttpServletResponse response)
	    throws ServletException, IOException {
	request.setAttribute(Constants.ATTRIBUTE_BLOCK, "block.jsp");
	request.getRequestDispatcher("views/items.jsp").forward(request,
		response);
    }

}
