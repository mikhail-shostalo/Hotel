package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.shostalo.SummaryTask4.entity.Request;
import ua.nure.shostalo.SummaryTask4.services.RequestService;

/**
 * Servlet implementation class RequestListServlet.
 */
@WebServlet("/requestList")
public class RequestListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger
	    .getLogger(RequestListServlet.class);

    /**
     * The request service.
     */
    private RequestService requestService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestListServlet() {
	LOGGER.info("constructor - RequestListServlet");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	requestService = (RequestService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_REQUEST_SERVICE);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	List<Request> requests = requestService.getRequests();
	request.setAttribute(Constants.ATTRIBUTE_REQUEST_LIST, requests);
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "main.jsp");
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
