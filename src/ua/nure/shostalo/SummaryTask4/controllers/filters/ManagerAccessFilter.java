package ua.nure.shostalo.SummaryTask4.controllers.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.shostalo.SummaryTask4.controllers.Constants;
import ua.nure.shostalo.SummaryTask4.entity.User;

/**
 * Servlet Filter implementation class ManagmentAccessFilter.
 */
@WebFilter("/ManagmentAccessFilter")
public class ManagerAccessFilter implements Filter {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger
	    .getLogger(ManagerAccessFilter.class);

    /**
     * Default constructor.
     */
    public ManagerAccessFilter() {
	LOGGER.info("constructor - ManagerAccessFilter");
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
	LOGGER.info("destroy - ManagerAccessFilte");
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse,
     *      FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpServletResponse resp = (HttpServletResponse) response;
	HttpSession session = req.getSession();
	User user = (User) session.getAttribute(Constants.ATTRIBUTE_USER);
	if (user != null
		&& user.getRole().equals(Constants.PARAMETR_MANAGER_ROLE)) {
	    chain.doFilter(request, response);
	} else {
	    resp.sendRedirect("items");
	}
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
	// TODO Auto-generated method stub
    }

}
