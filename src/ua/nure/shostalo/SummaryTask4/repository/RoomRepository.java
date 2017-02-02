package ua.nure.shostalo.SummaryTask4.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.shostalo.SummaryTask4.entity.ClassRoom;
import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.Room;

/**
 * The room repository.
 * 
 * @author Mikhail Shostalo
 *
 */
public class RoomRepository {

    /**
     * The sql query for gets rooms.
     */
    private static final String GET_ROOMS_RU = "select Rooms.idRoom, Rooms.idClass, "
	    + "statusRoomRu, classNameRu, "
	    + "descriptionRu, numberOfPlaces,price, numberOfRoom "
	    + "from Rooms inner join StatusRoom "
	    + "on Rooms.idStatusRoom = StatusRoom.idStatusRoom "
	    + "join ClassRoom " + "on Rooms.idClass = ClassRoom.idClass";

    /**
     * The sql query for gets rooms.
     */
    private static final String GET_ROOMS_EN = "select Rooms.idRoom, Rooms.idClass, "
	    + "statusRoomEn, classNameEn, "
	    + "descriptionEn, numberOfPlaces,price, numberOfRoom "
	    + "from Rooms inner join StatusRoom "
	    + "on Rooms.idStatusRoom = StatusRoom.idStatusRoom "
	    + "join ClassRoom " + "on Rooms.idClass = ClassRoom.idClass";

    /**
     * The sql query for gets rooms by namber of people in room.
     */
    private static final String GET_ROOMS_BY_NUMBER_RU = "select Rooms.idRoom, Rooms.idClass, "
	    + "statusRoomRu, classNameRu, "
	    + "descriptionRu, numberOfPlaces,price, numberOfRoom "
	    + "from Rooms inner join StatusRoom "
	    + "on Rooms.idStatusRoom = StatusRoom.idStatusRoom "
	    + "join ClassRoom " + "on Rooms.idClass = ClassRoom.idClass "
	    + "where numberOfPlaces >= ?";

    /**
     * The sql query for gets rooms by namber of people in room.
     */
    private static final String GET_ROOMS_BY_NUMBER_EN = "select Rooms.idRoom, Rooms.idClass, "
	    + "statusRoomEn, classNameEn, "
	    + "descriptionEn, numberOfPlaces,price, numberOfRoom "
	    + "from Rooms inner join StatusRoom "
	    + "on Rooms.idStatusRoom = StatusRoom.idStatusRoom "
	    + "join ClassRoom " + "on Rooms.idClass = ClassRoom.idClass "
	    + "where numberOfPlaces >= ?";

    /**
     * The query gets room by id on russsian language.
     */
    private static final String GET_ROOM_BY_ID_RU = "select Rooms.idRoom, Rooms.idClass, "
	    + "statusRoomRu, classNameRu, "
	    + "descriptionRu, numberOfPlaces,price, numberOfRoom "
	    + "from Rooms inner join StatusRoom "
	    + "on Rooms.idStatusRoom = StatusRoom.idStatusRoom "
	    + "join ClassRoom " + "on Rooms.idClass = ClassRoom.idClass "
	    + "where Rooms.idRoom = ?";

    /**
     * The query gets room by id on english language.
     */
    private static final String GET_ROOM_BY_ID_EN = "select Rooms.idRoom, Rooms.idClass, "
	    + "statusRoomEn, classNameEn, "
	    + "descriptionEn, numberOfPlaces,price, numberOfRoom "
	    + "from Rooms inner join StatusRoom "
	    + "on Rooms.idStatusRoom = StatusRoom.idStatusRoom "
	    + "join ClassRoom " + "on Rooms.idClass = ClassRoom.idClass "
	    + "where Rooms.idRoom = ?";

    /**
     * The query gets room class on russian.
     */
    private static final String GET_CLASSES_ROOM_RU = "select idClass, classNameRu, "
	    + "descriptionRu, numberOfPlaces, price from ClassRoom";

    /**
     * The query gets room class by id.
     */
    private static final String GET_CLASSES_ROOM_BY_ID = "select idClass, classNameRu, "
	    + "descriptionRu, numberOfPlaces, price from ClassRoom "
	    + "where idClass = ?";

    /**
     * The query gets room class on english.
     */
    private static final String GET_CLASSES_ROOM_EN = "select idClass, classNameEn, "
	    + "descriptionEn, numberOfPlaces, price from ClassRoom";

    /**
     * The query retutns id of room status.
     */
    private static final String GET_STATUS_ID = "select idStatusRoom "
	    + "from StatusRoom where StatusRoomEn = ?";

    /**
     * The query updete rows in data base.
     */
    private static final String UPDATE_STATUS = "update Rooms "
	    + "set idStatusRoom = ? where idRoom = ?";

    /**
     * Gets all rooms.
     * 
     * @param language
     *            the language
     * @param connection
     *            the object {@link Connection}
     * @return all rooms
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final List<Room> getRooms(final Language language,
	    final Connection connection) throws SQLException {
	String query = "";
	List<Room> list = new ArrayList<>();
	switch (language) {
	case EN:
	    query = GET_ROOMS_EN;
	    break;
	default:
	    query = GET_ROOMS_RU;
	    break;
	}
	try (PreparedStatement ps = connection.prepareStatement(query)) {
	    ResultSet result = ps.executeQuery();
	    while (result.next()) {
		list.add(createRoom(result));
	    }
	}
	return list;
    }

    /**
     * Gets rooms by number of people.
     * 
     * @param numberOfPlace
     *            the number of people
     * @param language
     *            the language
     * @param connection
     *            the object {@link Connection}
     * @return all rooms
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final List<Room> getRoomsByNumberOfPeople(
	    final Integer numberOfPlace, final Language language,
	    final Connection connection) throws SQLException {
	String query = "";
	List<Room> list = new ArrayList<>();
	switch (language) {
	case EN:
	    query = GET_ROOMS_BY_NUMBER_EN;
	    break;
	default:
	    query = GET_ROOMS_BY_NUMBER_RU;
	    break;
	}
	try (PreparedStatement ps = connection.prepareStatement(query)) {
	    ps.setInt(1, numberOfPlace);
	    ResultSet result = ps.executeQuery();
	    while (result.next()) {
		list.add(createRoom(result));
	    }
	}
	return list;
    }

    /**
     * Gets all rooms.
     * 
     * @param language
     *            the language
     * @param connection
     *            the object {@link Connection}
     * @return all rooms
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final List<ClassRoom> getRoomsClasses(final Language language,
	    final Connection connection) throws SQLException {
	String query = "";
	switch (language) {
	case EN:
	    query = GET_CLASSES_ROOM_EN;
	    break;
	default:
	    query = GET_CLASSES_ROOM_RU;
	    break;
	}
	try (PreparedStatement ps = connection.prepareStatement(query)) {
	    List<ClassRoom> list = new ArrayList<>();
	    ResultSet result = ps.executeQuery();
	    while (result.next()) {
		list.add(createClassRoom(result));
	    }
	    return list;
	}
    }

    /**
     * Returns the room by id.
     * 
     * @param id
     *            the id of room
     * @param language
     *            the language
     * @param connection
     *            the connection with data base.
     * @return the room by id
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final Room getRoomById(final Integer id, final Language language,
	    final Connection connection) throws SQLException {
	Room room = null;
	String sql = "";
	switch (language) {
	case EN:
	    sql = GET_ROOM_BY_ID_EN;
	    break;
	default:
	    sql = GET_ROOM_BY_ID_RU;
	    break;
	}
	try (PreparedStatement ps = connection.prepareStatement(sql)) {
	    ps.setInt(1, id);
	    ResultSet result = ps.executeQuery();
	    if (result.next()) {
		room = createRoom(result);
	    }
	}
	return room;
    }

    /**
     * Returns class of room by id.
     * 
     * @param id
     *            the id
     * @param connection
     *            the {@link Connection}
     * @return class of room by id
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final ClassRoom getRoomClassbyId(final Integer id,
	    final Connection connection) throws SQLException {
	ClassRoom classRoom = null;
	try (PreparedStatement ps = connection
		.prepareStatement(GET_CLASSES_ROOM_BY_ID)) {
	    ps.setInt(1, id);
	    ResultSet result = ps.executeQuery();
	    if (result.next()) {
		classRoom = createClassRoom(result);
	    }
	}
	return classRoom;
    }

    /**
     * Updates status of room.
     * 
     * @param room
     *            the room
     * @param status
     *            the room status
     * @param connection
     *            the connection with data base
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final void updateStatus(final Room room, final String status,
	    final Connection connection) throws SQLException {
	Integer id = getStausId(status, connection);
	try (PreparedStatement ps = connection
		.prepareStatement(UPDATE_STATUS)) {
	    int index = 1;
	    ps.setInt(index++, id);
	    ps.setInt(index, room.getId());
	    ps.executeUpdate();
	}
    }

    /**
     * Returns status by id.
     * 
     * @param status
     *            the status
     * @param connection
     *            the connection with data base
     * @return status by id
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    private Integer getStausId(final String status, final Connection connection)
	    throws SQLException {
	try (PreparedStatement ps = connection
		.prepareStatement(GET_STATUS_ID)) {
	    ps.setString(1, status);
	    ResultSet result = ps.executeQuery();
	    if (result.next()) {
		return result.getInt(1);
	    }
	}
	return null;
    }

    /**
     * Creates class of room.
     * 
     * @param result
     *            the {@link ResultSet}
     * @return class of room
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    private ClassRoom createClassRoom(final ResultSet result)
	    throws SQLException {
	int index = 1;
	Integer id = result.getInt(index++);
	String classRoom = result.getString(index++);
	String description = result.getString(index++);
	Integer numberOfPlaces = result.getInt(index++);
	Double price = result.getDouble(index++);
	return new ClassRoom(id, classRoom, numberOfPlaces, price, description);
    }

    /**
     * Creates room.
     * 
     * @param result
     *            the {@link ResultSet}
     * @return the room
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    private Room createRoom(final ResultSet result) throws SQLException {
	int index = 1;
	Integer idRoom = result.getInt(index++);
	Integer idClass = result.getInt(index++);
	String status = result.getString(index++);
	String classRoom = result.getString(index++);
	String description = result.getString(index++);
	Integer numberOfPlaces = result.getInt(index++);
	Double price = result.getDouble(index++);
	Integer numberOfRoom = result.getInt(index++);
	ClassRoom roomClass = new ClassRoom(idClass, classRoom, numberOfPlaces,
		price, description);
	return new Room(idRoom, numberOfRoom, status, roomClass);
    }
}
