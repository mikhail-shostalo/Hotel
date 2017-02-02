package ua.nure.shostalo.SummaryTask4.entity;

/**
 * The languages enum.
 * 
 * @author Mikhail Shostalo
 *
 */
public enum Language {

    /**
     * The languages values.
     */
    RU("ru_RU"), EN("en_US");

    /**
     * The value.
     */
    private final String value;

    /**
     * The Language constructor.
     * 
     * @param value
     *            the value of enum
     */
    Language(final String value) {
	this.value = value;
    }

    /**
     * Return the enum by value.
     * 
     * @param v
     *            the value.
     * @return the enum by value.
     */
    public static Language fromValue(final String v) {
	for (Language c : Language.values()) {
	    if (c.value.equals(v)) {
		return c;
	    }
	}
	return Language.RU;
    }
}
