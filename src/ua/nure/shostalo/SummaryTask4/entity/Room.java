package ua.nure.shostalo.SummaryTask4.entity;

/**
 * The class describes entity room.
 * 
 * @author Mikhail Shostalo
 *
 */
public class Room {

    /**
     * The room id.
     */
    private Integer id;

    /**
     * A room number.
     */
    private Integer roomNumber;

    /**
     * A status of room.
     */
    private String status;

    /**
     * A class of room.
     */
    private ClassRoom classRoom;

    /**
     * Creates object {@link Room}.
     * 
     * @param id
     *            the room id
     * @param roomNumber
     *            the room number
     * @param status
     *            the room status
     * @param classRoom
     *            the room class
     */
    public Room(final Integer id, final Integer roomNumber, final String status,
	    final ClassRoom classRoom) {
	this.id = id;
	this.roomNumber = roomNumber;
	this.status = status;
	this.classRoom = classRoom;
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
     * @return the roomNumber
     */
    public final Integer getRoomNumber() {
	return roomNumber;
    }

    /**
     * @param roomNumber
     *            the roomNumber to set
     */
    public final void setRoomNumber(final Integer roomNumber) {
	if (roomNumber > 0) {
	    this.roomNumber = roomNumber;
	}
    }

    /**
     * @return the status
     */
    public final String getStatus() {
	return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public final void setStatus(final String status) {
	this.status = status;
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
    public final void setClassRoom(ClassRoom classRoom) {
	this.classRoom = classRoom;
    }

}