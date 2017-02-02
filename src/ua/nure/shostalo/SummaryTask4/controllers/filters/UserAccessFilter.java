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

/**
 * Servlet Filter implementation class UserAccesFilter.
 */
@WebFilter("/UserAccesFilter")
public class UserAccessFilter implements Filter {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger
	    .getLogger(UserAccessFilter.class);

    /**
     * Default constructor.
     */
    public UserAccessFilter() {
	LOGGER.info("constructor - UserAccessFilter");
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
	LOGGER.info("destroy - UserAccessFilter");
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
	if (session.getAttribute(Constants.ATTRIBUTE_USER) == null) {
	    resp.sendRedirect("items");
	} else {
	    chain.doFilter(request, response);
	}
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
	// TODO Auto-generated method stub
    }

}
