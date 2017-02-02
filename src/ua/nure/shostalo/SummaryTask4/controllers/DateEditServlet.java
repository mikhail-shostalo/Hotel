package ua.nure.shostalo.SummaryTask4.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.shostalo.SummaryTask4.entity.DateManagement;

/**
 * Servlet implementation class DateEditServlet.
 */
@WebServlet("/dateEdit")
public class DateEditServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger
	    .getLogger(DateEditServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateEditServlet() {
	LOGGER.info("constructor - DateEditServlet");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String arrival = request.getParameter("date1");
	String dep = request.getParameter("date2");
	Double price = Double.parseDouble(request.getParameter("price"));
	price = DateManagement.getPrice(arrival, dep, price);
	PrintWriter out = response.getWriter();
	out.print(price);
	out.flush();
	out.close();
    }
}
