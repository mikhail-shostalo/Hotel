package ua.nure.shostalo.SummaryTask4.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nure.shostalo.SummaryTask4.entity.User;

/**
 * The users repository.
 * 
 * @author Mikhail Shostalo
 *
 */
public class UserRepository {

    /**
     * The sql query inserts a new user.
     */
    private static final String INSERT_USER = "insert into Users"
	    + " (name, lastName, login, password, email, phoneNumber, idRole)"
	    + " values (?, ?, ?, ?, ?, ?, ?)";

    /**
     * The sql query for gets role id.
     */
    private static final String GET_ROLE_ID = "select idRole from Roles"
	    + " where role = ?";

    /**
     * The sql query for gets a user.
     */
    private static final String GET_USER = "select name, lastName, "
	    + "login, password, email, phoneNumber, role"
	    + " from Users inner join Roles on Users.idRole = Roles.idRole"
	    + " where login = ?";

    /**
     * Insert a new user in data base.
     * 
     * @param user
     *            the user
     * @param connection
     *            {@link Connection}
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final void addUser(final User user, final Connection connection)
	    throws SQLException {
	Integer id = getIdRole(user.getRole(), connection);
	try (PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {
	    int index = 1;
	    ps.setString(index++, user.getName());
	    ps.setString(index++, user.getLastName());
	    ps.setString(index++, user.getLogin());
	    ps.setString(index++, user.getPassword());
	    ps.setString(index++, user.getEmail());
	    ps.setString(index++, user.getMobilePhone());
	    ps.setInt(index++, id);
	    ps.executeUpdate();
	}
    }

    /**
     * Returns user by login.
     * 
     * @param login
     *            the login
     * @param connection
     *            {@link Connection}
     * @return the user
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final User getUser(final String login, final Connection connection)
	    throws SQLException {
	User user = null;
	try (PreparedStatement ps = connection.prepareStatement(GET_USER)) {
	    ps.setString(1, login);
	    ResultSet result = ps.executeQuery();
	    if (result.next()) {
		user = createUser(result);
	    }
	}
	return user;
    }

    /**
     * Creates object {@link User} by {@link ResultSet}.
     * 
     * @param result
     *            {@link ResultSet}
     * @return the user
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    private User createUser(final ResultSet result) throws SQLException {
	int index = 1;
	String name = result.getString(index++);
	String lastName = result.getString(index++);
	String login = result.getString(index++);
	String password = result.getString(index++);
	String email = result.getString(index++);
	String phoneNumber = result.getString(index++);
	String role = result.getString(index++);
	return new User(name, lastName, login, password, email, phoneNumber,
		role);
    }

    /**
     * Gets id of role.
     * 
     * @param role
     *            the role name
     * @param connection
     *            {@link Connection}
     * @return id
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    private Integer getIdRole(final String role, final Connection connection)
	    throws SQLException {
	Integer id = null;
	try (PreparedStatement ps = connection.prepareStatement(GET_ROLE_ID)) {
	    int index = 1;
	    ps.setString(index, role);
	    ResultSet result = ps.executeQuery();
	    if (result.next()) {
		id = result.getInt(index);
	    }
	}
	return id;
    }
}
