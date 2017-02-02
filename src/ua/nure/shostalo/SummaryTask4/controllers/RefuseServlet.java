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

import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.User;
import ua.nure.shostalo.SummaryTask4.services.AnswerService;

/**
 * Servlet implementation class RefuseServlet.
 */
@WebServlet("/refuse")
public class RefuseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RefuseServlet.class);

    /**
     * The service for answers.
     */
    private AnswerService answerService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefuseServlet() {
	LOGGER.info("constructor - RefuseServlet");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	answerService = (AnswerService) config.getServletContext()
		.getAttribute(Constants.ATTRIBUTE_ANSWER_SERVICE);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	Integer id = Integer
		.parseInt(request.getParameter(Constants.PARAMETR_ANSWER_ID));
	answerService.delete(id);
	User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
	String locale = (String) session
		.getAttribute(Constants.ATTRIBUTE_LOCALE);
	session.setAttribute(Constants.ATTRIBUTE_ANSWER_COUNT,
		answerService
			.getAnswers(user.getLogin(), Language.fromValue(locale))
			.size());
	response.sendRedirect("message");
    }

}
