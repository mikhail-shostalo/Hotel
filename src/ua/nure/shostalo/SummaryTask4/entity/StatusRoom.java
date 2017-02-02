package ua.nure.shostalo.SummaryTask4.entity;

/**
 * The status of room enum.
 * 
 * @author Mikhail Shostalo
 *
 */
public enum StatusRoom {

    /**
     * The values of room status.
     */
    FREE("free"), RESERVED("reserved"), OCCUPIED("occupied"), INACCESSIBLE("inaccessible");

    /**
     * The value.
     */
    private final String value;

    /**
     * The {@link StatusRoom} constructor.
     * 
     * @param value
     *            the value.
     */
    StatusRoom(final String value) {
	this.value = value;
    }

    /**
     * Returns the value of enum.
     * 
     * @param status
     *            the enum
     * @return the value of enum
     */
    public static String getValue(final StatusRoom status) {
	return status.value;
    }

    /**
     * Returns the enum by value.
     * 
     * @param value
     *            the value
     * @return the enum by value
     */
    public static StatusRoom fromValue(final String value) {
	for (StatusRoom status : StatusRoom.values()) {
	    if (status.value.equals(value)) {
		return status;
	    }
	}
	throw new IllegalArgumentException();
    }
}