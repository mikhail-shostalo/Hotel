package ua.nure.shostalo.SummaryTask4.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * The managment of dates.
 * 
 * @author Mikhail Shostalo
 *
 */
public class DateManagement {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(DateManagement.class);

    /**
     * The constant set days.
     */
    private static final Integer TO_DAYS = 24 * 60 * 60 * 1000;

    /**
     * The private default constructor.
     */
    protected DateManagement() {
	// Default constructor.
    }

    /**
     * Returns price by all days.
     * 
     * @param firstDate
     *            the start date.
     * @param secondDate
     *            the end date.
     * @param price
     *            the price.
     * @return price by all days.
     * 
     */
    public static Double getPrice(final String firstDate,
	    final String secondDate, final Double price) {
	int days = getDays(firstDate, secondDate);
	return price * days;
    }

    /**
     * Returns number of days bettwen two dates.
     * 
     * @param firstDate
     *            the start date.
     * @param secondDate
     *            the end date.
     * @return number of days bettwen two dates.
     */
    public static Integer getDays(final String firstDate,
	    final String secondDate) {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date date1 = null;
	Date date2 = null;
	try {
	    date1 = format.parse(firstDate);
	    date2 = format.parse(secondDate);
	} catch (ParseException e) {
	    LOGGER.error(e);
	}
	long miliseconds = date2.getTime() - date1.getTime();
	int days = (int) (miliseconds / (TO_DAYS));
	return (days > 0) ? days : 1;
    }
}
