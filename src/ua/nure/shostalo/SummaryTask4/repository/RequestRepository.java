package ua.nure.shostalo.SummaryTask4.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.shostalo.SummaryTask4.entity.ClassRoom;
import ua.nure.shostalo.SummaryTask4.entity.Request;

/**
 * The request repository.
 * 
 * @author Mikhail Shostalo
 *
 */
public class RequestRepository {

    /**
     * The sql query insert the request.
     */
    private static final String INSERT_REQUEST = "insert into Request(login, idClass, "
	    + "arrivalDate, dateOfDeparture, numberOfPlaces) values (?, ?, ?, ?, ?)";

    /**
     * The query returns all requestes.
     */
    private static final String GET_REQUESTS = "select idRequest, Request.login,  ClassRoom.idClass,"
	    + " classNameRu, descriptionRu, ClassRoom.numberOfPlaces, price,  arrivalDate,"
	    + " dateOfDeparture, Request.numberOfPlaces from Request inner join Users"
	    + " on Request.login = Users.login "
	    + "join ClassRoom on Request.idClass = ClassRoom.idClass";

    /**
     * The query returns request by id.
     */
    private static final String GET_BY_ID = "select idRequest, Request.login,  ClassRoom.idClass,"
	    + " classNameRu, descriptionRu, ClassRoom.numberOfPlaces, price,  arrivalDate,"
	    + " dateOfDeparture, Request.numberOfPlaces from Request inner join Users"
	    + " on Request.login = Users.login "
	    + "join ClassRoom on Request.idClass = ClassRoom.idClass"
	    + " where idRequest = ?";

    /**
     * The query deletes request by id.
     */
    private static final String DELETE_BY_ID = "delete from Request where idRequest = ?";

    /**
     * The method inserts request of user in data base.
     * 
     * @param request
     *            the request
     * @param login
     *            the user login
     * @param connection
     *            the {@link Connection}
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final void add(final Request request, final String login,
	    final Connection connection) throws SQLException {
	try (PreparedStatement ps = connection
		.prepareStatement(INSERT_REQUEST)) {
	    int index = 1;
	    ps.setString(index++, login);
	    ps.setInt(index++, request.getClassRoom().getId());
	    ps.setString(index++, request.getArrivalDate());
	    ps.setString(index++, request.getDateOfDeparture());
	    ps.setInt(index++, request.getNumberOfPlace());
	    ps.executeUpdate();
	}
    }

    /**
     * Returns all requestes.
     * 
     * @param connection
     *            the connection with data base.
     * @return all requestes
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final List<Request> getRequests(final Connection connection)
	    throws SQLException {
	List<Request> request = new ArrayList<>();
	try (PreparedStatement ps = connection.prepareStatement(GET_REQUESTS)) {
	    ResultSet result = ps.executeQuery();
	    while (result.next()) {
		request.add(createRequest(result));
	    }
	}
	return request;
    }

    /**
     * Deletes request by id.
     * 
     * @param id
     *            the request id
     * @param connection
     *            the connection with data base
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final void delete(final Integer id, final Connection connection)
	    throws SQLException {
	try (PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID)) {
	    ps.setInt(1, id);
	    ps.executeUpdate();
	}
    }

    /**
     * Returns request by id.
     * 
     * @param id
     *            the id of request
     * @param connection
     *            the connection with data base
     * @return request by id
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final Request getById(final Integer id, final Connection connection)
	    throws SQLException {
	Request request = null;
	try (PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {
	    ps.setInt(1, id);
	    ResultSet result = ps.executeQuery();
	    if (result.next()) {
		request = createRequest(result);
	    }
	}
	return request;
    }

    /**
     * Creates the {@link Request}.
     * 
     * @param result
     *            the object {@link ResultSet}
     * @return the {@link Request}
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    private Request createRequest(final ResultSet result) throws SQLException {
	int index = 1;
	Integer id = result.getInt(index++);
	String login = result.getString(index++);
	Integer idClass = result.getInt(index++);
	String className = result.getString(index++);
	String descriptionClass = result.getString(index++);
	Integer numberPlaceClass = result.getInt(index++);
	Double price = result.getDouble(index++);
	String arrivDate = result.getString(index++);
	String depDate = result.getString(index++);
	Integer numberOfPlaces = result.getInt(index++);
	ClassRoom roomClass = new ClassRoom(idClass, className,
		numberPlaceClass, price, descriptionClass);
	return new Request(id, roomClass, login, numberOfPlaces, arrivDate,
		depDate);
    }

}