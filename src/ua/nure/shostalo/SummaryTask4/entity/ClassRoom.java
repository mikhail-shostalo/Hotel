package ua.nure.shostalo.SummaryTask4.entity;

/**
 * The class describes entity class of room.
 * 
 * @author Mikhail Shostalo
 *
 */
public class ClassRoom {

    /**
     * The id of class room.
     */
    private Integer id;

    /**
     * A class of room.
     */
    private String roomClass;

    /**
     * A number of place.
     */
    private Integer numberOfPlace;

    /**
     * The price for a night.
     */
    private Double price;

    /**
     * A description.
     */
    private String description;

    /**
     * Creates object {@link ClassRoom}.
     * 
     * @param id
     *            the class id
     * @param roomClass
     *            the class name
     * @param numberOfPlace
     *            the number of place
     * @param price
     *            the price of room
     * @param description
     *            the description
     */
    public ClassRoom(final Integer id, final String roomClass,
	    final Integer numberOfPlace, final Double price,
	    final String description) {
	this.id = id;
	this.roomClass = roomClass;
	this.numberOfPlace = numberOfPlace;
	this.price = price;
	this.description = description;
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
     * @return the roomClass
     */
    public final String getRoomClass() {
	return roomClass;
    }

    /**
     * @param roomClass
     *            the roomClass to set
     */
    public final void setRoomClass(final String roomClass) {
	this.roomClass = roomClass;
    }

    /**
     * @return the numberOfPlace
     */
    public final Integer getNumberOfPlace() {
	return numberOfPlace;
    }

    /**
     * @param numberOfPlace
     *            the numberOfPlace to set
     */
    public final void setNumberOfPlace(final Integer numberOfPlace) {
	this.numberOfPlace = numberOfPlace;
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
	this.price = price;
    }

    /**
     * @return the description
     */
    public final String getDescription() {
	return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public final void setDescription(final String description) {
	this.description = description;
    }

}
