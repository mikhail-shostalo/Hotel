package ua.nure.shostalo.SummaryTask4.util;

/**
 * The sort enum.
 * 
 * @author Mikhail Shostalo
 *
 */
public enum SortEnum {

    /**
     * The enums.
     */
    PRICE("price"), PLACES("places"), CLASS("class"), STATUS("status");

    /**
     * The value.
     */
    private final String value;

    /**
     * The {@link SortEnum} constructor.
     * 
     * @param value
     *            the value
     */
    SortEnum(final String value) {
	this.value = value;
    }

    /**
     * Returns enum by value.
     * 
     * @param value
     *            the value
     * @return enum by value
     */
    public static SortEnum fromValue(final String value) {
	for (SortEnum status : SortEnum.values()) {
	    if (status.value.equals(value)) {
		return status;
	    }
	}
	throw new IllegalArgumentException();
    }
}
