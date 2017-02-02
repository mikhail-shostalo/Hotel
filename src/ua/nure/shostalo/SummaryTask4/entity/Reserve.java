package ua.nure.shostalo.SummaryTask4.entity;

/**
 * The class describes entity reserve.
 * 
 * @author Mikhail Shostalo
 *
 */
public class Reserve {

    /**
     * The id of reserve.
     */
    private Integer id;

    /**
     * The room.
     */
    private Room room;

    /**
     * The date of reserve.
     */
    private String dateReserve;

    /**
     * The arrival date.
     */
    private String arrivalDate;

    /**
     * The date of departure.
     */
    private String dateOfDepartures;

    /**
     * The status of reserve.
     */
    private String statusReserve;

    /**
     * The price.
     */
    private Double price;

    /**
     * 
     * @param id
     *            the id of reserve
     * @param room
     *            the room of reserve
     * @param dateReserve
     *            the date of reserve
     * @param arrivalDate
     *            the arrival date
     * @param depDate
     *            The date of departure
     * @param statusReserve
     *            the status of reserve
     * @param price
     *            the summary price
     */
    public Reserve(final Integer id, final Room room, final String dateReserve,
	    final String arrivalDate, final String depDate,
	    final String statusReserve, final Double price) {
	this.id = id;
	this.room = room;
	this.dateReserve = dateReserve;
	this.arrivalDate = arrivalDate;
	this.dateOfDepartures = depDate;
	this.statusReserve = statusReserve;
	this.price = price;
    }

    /**
     * 
     * @param room
     *            the room of reserve
     * @param dateReserve
     *            the date of reserve
     * @param arrivalDate
     *            the arrival date
     * @param depDate
     *            The date of departure
     * @param statusReserve
     *            the status of reserve
     * @param price
     *            the summary price
     */
    public Reserve(final Room room, final String dateReserve,
	    final String arrivalDate, final String depDate,
	    final String statusReserve, final Double price) {
	this.room = room;
	this.dateReserve = dateReserve;
	this.arrivalDate = arrivalDate;
	this.dateOfDepartures = depDate;
	this.statusReserve = statusReserve;
	this.price = price;
    }

    /**
     * @return the id
     */
    public final Integer getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(final Integer id) {
	this.id = id;
    }

    /**
     * @return the room
     */
    public final Room getRoom() {
	return room;
    }

    /**
     * @param room
     *            the room to set
     */
    public final void setRoom(final Room room) {
	this.room = room;
    }

    /**
     * @return the dateReserve
     */
    public final String getDateReserve() {
	return dateReserve;
    }

    /**
     * @param dateReserve
     *            the dateReserve to set
     */
    public final void setDateReserve(final String dateReserve) {
	this.dateReserve = dateReserve;
    }

    /**
     * @return the arrivalDate
     */
    public final String getArrivalDate() {
	return arrivalDate;
    }

    /**
     * @param arrivalDate
     *            the arrivalDate to set
     */
    public final void setArrivalDate(final String arrivalDate) {
	this.arrivalDate = arrivalDate;
    }

    /**
     * @return the dateOfDepartures
     */
    public final String getDateOfDepartures() {
	return dateOfDepartures;
    }

    /**
     * @param dateOfDepartures
     *            the dateOfDepartures to set
     */
    public final void setDateOfDepartures(final String dateOfDepartures) {
	this.dateOfDepartures = dateOfDepartures;
    }

    /**
     * @return the statusReserve
     */
    public final String getStatusReserve() {
	return statusReserve;
    }

    /**
     * @param statusReserve
     *            the statusReserve to set
     */
    public final void setStatusReserve(final String statusReserve) {
	this.statusReserve = statusReserve;
    }

    /**
     * @return the price
     */
    public final Double getPrice() {
	return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public final void setPrice(final Double price) {
	if (price > 0) {
	    this.price = price;
	}
    }

}
