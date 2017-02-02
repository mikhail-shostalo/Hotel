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

import ua.nure.shostalo.SummaryTask4.entity.Answer;
import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.User;
import ua.nure.shostalo.SummaryTask4.services.AnswerService;

/**
 * Servlet implementation class MessageServlet.
 */
@WebServlet("/message")
public class MessageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(MessageServlet.class);

    /**
     * The service for answers.
     */
    private AnswerService answerService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
	LOGGER.info("constructor - MessageServlet");
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
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
	String language = (String) session
		.getAttribute(Constants.ATTRIBUTE_LOCALE);
	List<Answer> listAnswers = answerService.getAnswers(user.getLogin(),
		Language.fromValue(language));
	request.setAttribute(Constants.ATTRIBUTE_ANSWER_LIST, listAnswers);
	request.setAttribute(Constants.ATTRIBUTE_PAGE, "messages.jsp");
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
