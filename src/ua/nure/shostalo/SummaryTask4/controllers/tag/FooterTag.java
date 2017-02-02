package ua.nure.shostalo.SummaryTask4.controllers.tag;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * The user tag.
 * 
 * @author Mikhail Shostalo
 *
 */
public class FooterTag extends TagSupport {

    /**
     * The serial version of the class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(FooterTag.class);

    @Override
    public int doStartTag() throws JspException {
	Date nowDate = new Date();
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(nowDate);
	StringBuilder footer = new StringBuilder();
	footer.append("<p class='text-muted'>&copy; Grand Hotel ")
		.append(calendar.get(Calendar.YEAR)).append("</p>");
	try {
	    JspWriter out = pageContext.getOut();
	    out.write(footer.toString());
	} catch (IOException e) {
	    LOGGER.error(e);
	}
	return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
	return EVAL_PAGE;
    }
}