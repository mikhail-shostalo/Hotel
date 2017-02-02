package ua.nure.shostalo.SummaryTask4.entity;

/**
 * The class describes entity answer.
 * 
 * @author Mikhail Shostalo
 *
 */
public class Answer {

    /**
     * Answer id.
     */
    private Integer id;

    /**
     * The room.
     */
    private Room room;

    /**
     * The arrival date.
     */
    private String arrivalDate;

    /**
     * The date of departure.
     */
    private String dateOfDeparture;

    /**
     * The price.
     */
    private Double price;

    /**
     * Create object {@link Answer}.
     * 
     * @param id
     *            the answer id
     * @param room
     *            the room
     * @param arrivalDate
     *            the arrival date
     * @param depDate
     *            the date of departure
     * @param price
     *            the price
     */
    public Answer(final Integer id, final Room room, final String arrivalDate,
	    final String depDate, final Double price) {
	this.id = id;
	this.room = room;
	this.price = price;
	this.arrivalDate = arrivalDate;
	this.dateOfDeparture = depDate;
    }

    /**
     * @return the arrivalDate
     */
    public String getArrivalDate() {
	return arrivalDate;
    }

    /**
     * @param arrivalDate
     *            the arrivalDate to set
     */
    public void setArrivalDate(String arrivalDate) {
	this.arrivalDate = arrivalDate;
    }

    /**
     * @return the dateOfDeparture
     */
    public String getDateOfDeparture() {
	return dateOfDeparture;
    }

    /**
     * @param dateOfDeparture
     *            the dateOfDeparture to set
     */
    public void setDateOfDeparture(String dateOfDeparture) {
	this.dateOfDeparture = dateOfDeparture;
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
