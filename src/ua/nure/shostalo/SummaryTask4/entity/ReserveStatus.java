package ua.nure.shostalo.SummaryTask4.entity;

/**
 * The status of reserve enum.
 * 
 * @author Mikhail Shostalo
 *
 */
public enum ReserveStatus {

    /**
     * The values of reserve status.
     */
    BOOKED("booked"), PAID("paid");

    /**
     * The value.
     */
    private final String value;

    /**
     * The {@link ReserveStatus} constructor.
     * 
     * @param value
     *            the value of enum.
     */
    ReserveStatus(final String value) {
	this.value = value;
    }

    /**
     * Return the value of enum.
     * 
     * @param status
     *            the enum
     * @return the value of enum
     */
    public static String getValue(final ReserveStatus status) {
	return status.value;
    }

    /**
     * The enum by value.
     * 
     * @param value
     *            the value
     * @return enum by value
     */
    public static ReserveStatus fromValue(final String value) {
	for (ReserveStatus status : ReserveStatus.values()) {
	    if (status.value.equals(value)) {
		return status;
	    }
	}
	throw new IllegalArgumentException();
    }
}
