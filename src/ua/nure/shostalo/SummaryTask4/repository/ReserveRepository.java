package ua.nure.shostalo.SummaryTask4.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.Reserve;
import ua.nure.shostalo.SummaryTask4.entity.Room;

/**
 * The reserve repository.
 * 
 * @author Mikhail Shostalo
 *
 */
public class ReserveRepository {

    /**
     * The sql query inserts the reserve in data base.
     */
    private static final String INSERT_RESERVE = "insert into Reserves"
	    + "(dateReserve, dateArrival, dateOfDeparture, "
	    + "idRoom, login, idStatusReserve, price) "
	    + "values (?, ?, ?, ?, ?, ?, ?)";

    /**
     * The query gets status by id.
     */
    private static final String GET_STATUS_ID = "select idStatusReserve"
	    + " from StatusReserve" + " where StatusReserveEn = ?";

    /**
     * The query get reserves by login in russian.
     */
    private static final String GET_RESERVE_BY_LOGIN_RU = "select Reserves.idReserve, "
	    + "dateReserve,Reserves.dateArrival, Reserves.dateOfDeparture, "
	    + "Reserves.idRoom, StatusReserveRu, Reserves.price "
	    + "from Reserves inner join StatusReserve "
	    + "on Reserves.idStatusReserve = StatusReserve.idStatusReserve "
	    + "where Reserves.login = ? and StatusReserveEn = ?";

    /**
     * The query get reserves by login in english.
     */
    private static final String GET_RESERVE_BY_LOGIN_EN = "select Reserves.idReserve, "
	    + "dateReserve,Reserves.dateArrival, Reserves.dateOfDeparture, "
	    + "Reserves.idRoom, StatusReserveEn, Reserves.price "
	    + "from Reserves inner join StatusReserve "
	    + "on Reserves.idStatusReserve = StatusReserve.idStatusReserve "
	    + "where Reserves.login = ? and StatusReserveEn = ?";

    /**
     * The query update status of reserve.
     */
    private static final String UPDATE_STATUS = "update Reserves "
	    + "set idStatusReserve = ? where idReserve = ?";

    /**
     * The query deletes reserve by id.
     */
    private static final String DELETE_RESERVE = "delete from Reserves "
	    + "where idReserve = ?";

    /**
     * Adds new reserve.
     * 
     * @param reserve
     *            the reserve
     * @param login
     *            the user login
     * @param connection
     *            the connection with data base
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final void add(final Reserve reserve, final String login,
	    final Connection connection) throws SQLException {
	Integer idStatus = getStatusId(reserve.getStatusReserve(), connection);

	try (PreparedStatement ps = connection
		.prepareStatement(INSERT_RESERVE)) {
	    int index = 1;
	    ps.setString(index++, reserve.getDateReserve());
	    ps.setString(index++, reserve.getArrivalDate());
	    ps.setString(index++, reserve.getDateOfDepartures());
	    ps.setInt(index++, reserve.getRoom().getId());
	    ps.setString(index++, login);
	    ps.setInt(index++, idStatus);
	    ps.setDouble(index++, reserve.getPrice());
	    ps.executeUpdate();
	}
    }

    /**
     * Return list of reserves.
     * 
     * @param login
     *            the user login
     * @param status
     *            the status of reserve
     * @param language
     *            the language
     * @param connection
     *            the connection with data base
     * @return list of reserves.
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final List<Reserve> getReserve(final String login,
	    final String status, final Language language,
	    final Connection connection) throws SQLException {
	RoomRepository roomRepository = new RoomRepository();
	String sql = "";
	List<Reserve> list = new ArrayList<>();
	switch (language) {
	case EN:
	    sql = GET_RESERVE_BY_LOGIN_EN;
	    break;
	default:
	    sql = GET_RESERVE_BY_LOGIN_RU;
	    break;
	}
	try (PreparedStatement ps = connection.prepareStatement(sql)) {
	    ps.setString(1, login);
	    ps.setString(2, status);
	    ResultSet result = ps.executeQuery();
	    while (result.next()) {
		int index = 1;
		Integer id = result.getInt(index++);
		String dateReserve = result.getString(index++);
		String dateArrival = result.getString(index++);
		String dateDep = result.getString(index++);
		Integer idRoom = result.getInt(index++);
		Room room = roomRepository.getRoomById(idRoom, language,
			connection);
		String roomStatus = result.getString(index++);
		Double price = result.getDouble(index++);
		list.add(new Reserve(id, room, dateReserve, dateArrival,
			dateDep, roomStatus, price));
	    }
	}
	return list;
    }

    /**
     * Deletes reserve by id.
     * 
     * @param id
     *            the reserve id
     * @param connection
     *            the connection with data base
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final void delete(final Integer id, final Connection connection)
	    throws SQLException {
	try (PreparedStatement ps = connection
		.prepareStatement(DELETE_RESERVE)) {
	    ps.setInt(1, id);
	    ps.executeUpdate();
	}
    }

    /**
     * Updates the status of reserve.
     * 
     * @param status
     *            the status reserve
     * @param id
     *            the status id
     * @param connection
     *            the connection with data base
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final void updateStatus(final String status, final Integer id,
	    final Connection connection) throws SQLException {
	Integer idStatus = getStatusId(status, connection);
	try (PreparedStatement ps = connection
		.prepareStatement(UPDATE_STATUS)) {
	    ps.setInt(1, idStatus);
	    ps.setInt(2, id);
	    ps.executeUpdate();
	}
    }

    /**
     * Returns status of reserve by id.
     * 
     * @param status
     *            the status of reserve
     * @param connection
     *            the connection with data base
     * @return status of reserve by id
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    private Integer getStatusId(final String status,
	    final Connection connection) throws SQLException {
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
}