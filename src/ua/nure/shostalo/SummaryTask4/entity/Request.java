package ua.nure.shostalo.SummaryTask4.entity;

/**
 * The class describes entity request.
 * 
 * @author Mikhail Shostalo
 *
 */
public class Request {

    /**
     * The request id.
     */
    private Integer id;

    /**
     * The class of room.
     */
    private ClassRoom classRoom;

    /**
     * The user login.
     */
    private String login;

    /**
     * The number of place.
     */
    private Integer numberOfPlace;

    /**
     * The arrival date.
     */
    private String arrivalDate;

    /**
     * The date of departure.
     */
    private String dateOfDeparture;

    /**
     * Creates object {@link Request}.
     * 
     * @param id
     *            the request id
     * @param classRoom
     *            the class of room
     * @param login
     *            the user login
     * @param numberOfPlace
     *            the number of place
     * @param arrivalDate
     *            the arrival date
     * @param dateOfDeparture
     *            the date of Departure
     */
    public Request(final Integer id, final ClassRoom classRoom,
	    final String login, final Integer numberOfPlace,
	    final String arrivalDate, final String dateOfDeparture) {
	this.id = id;
	this.classRoom = classRoom;
	this.login = login;
	this.numberOfPlace = numberOfPlace;
	this.arrivalDate = arrivalDate;
	this.dateOfDeparture = dateOfDeparture;
    }

    /**
     * Creates object {@link Request}.
     * 
     * @param classRoom
     *            the class of room
     * @param numberOfPlace
     *            the number of place
     * @param arrivalDate
     *            the arrival date
     * @param dateOfDeparture
     *            the date of Departure
     */
    public Request(final ClassRoom classRoom, final String login,
	    final Integer numberOfPlace, final String arrivalDate,
	    final String dateOfDeparture) {
	this.classRoom = classRoom;
	this.login = login;
	this.numberOfPlace = numberOfPlace;
	this.arrivalDate = arrivalDate;
	this.dateOfDeparture = dateOfDeparture;
    }

    /**
     * @return the login.
     */
    public final String getLogin() {
	return login;
    }

    /**
     * @param login
     *            the login to set
     */
    public final void setLogin(String login) {
	this.login = login;
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
     * @return the classRoom
     */
    public final ClassRoom getClassRoom() {
	return classRoom;
    }

    /**
     * @param classRoom
     *            the classRoom to set
     */
    public final void setClassRoom(final ClassRoom classRoom) {
	this.classRoom = classRoom;
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
     * @return the dateOfDeparture
     */
    public final String getDateOfDeparture() {
	return dateOfDeparture;
    }

    /**
     * @param dateOfDeparture
     *            the dateOfDeparture to set
     */
    public final void setDateOfDeparture(final String dateOfDeparture) {
	this.dateOfDeparture = dateOfDeparture;
    }

}