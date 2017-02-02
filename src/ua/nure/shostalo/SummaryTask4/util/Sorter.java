package ua.nure.shostalo.SummaryTask4.util;

import java.util.Comparator;

import ua.nure.shostalo.SummaryTask4.entity.Room;

/**
 * Contains static methods for sorting.
 * 
 * @author Mikhail Shostalo
 *
 */
public class Sorter {

    /**
     * The private constructor.
     */
    protected Sorter() {

    }

    /**
     * The comparator for sorting rooms by price.
     */
    public static final Comparator<Room> SORT_ROOM_BY_PRICE = new Comparator<Room>() {
	@Override
	public int compare(final Room o1, final Room o2) {
	    return o1.getClassRoom().getPrice()
		    .compareTo(o2.getClassRoom().getPrice());
	}
    };

    /**
     * The comparator for sorting rooms by number of places.
     */
    public static final Comparator<Room> SORT_ROOM_BY_PLACES = new Comparator<Room>() {
	@Override
	public int compare(final Room o1, final Room o2) {
	    return o1.getClassRoom().getNumberOfPlace()
		    .compareTo(o2.getClassRoom().getNumberOfPlace());
	}
    };

    /**
     * The comparator for sorting rooms by room class.
     */
    public static final Comparator<Room> SORT_ROOM_BY_CLASS = new Comparator<Room>() {
	@Override
	public int compare(final Room o1, final Room o2) {
	    return o1.getClassRoom().getRoomClass()
		    .compareTo(o2.getClassRoom().getRoomClass());
	}
    };

    /**
     * The comparator for sorting rooms by room status.
     */
    public static final Comparator<Room> SORT_ROOM_BY_STATUS = new Comparator<Room>() {
	@Override
	public int compare(final Room o1, final Room o2) {
	    return o1.getStatus().compareTo(o2.getStatus());
	}
    };
}
