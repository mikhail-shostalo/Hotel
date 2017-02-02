package ua.nure.shostalo.SummaryTask4.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.shostalo.SummaryTask4.entity.Answer;
import ua.nure.shostalo.SummaryTask4.entity.ClassRoom;
import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.Room;

/**
 * The answer repository.
 * 
 * @author Mikhail Shostalo
 *
 */
public class AnswerRepository {

    /**
     * The query for inserting in data base.
     */
    private static final String INSERT_ANSWER = "insert into Answer(idRoom, login, price, "
	    + "arrivalDate, dateOfDeparture) values(?, ?, ?, ?, ?)";

    /**
     * The query gets answers in russian language.
     */
    private static final String GET_ANSWERS_RU = "select idAnswer, Answer.idRoom, "
	    + "arrivalDate, dateOfDeparture, numberOfRoom, StatusRoomRu, "
	    + "ClassRoom.idClass,  ClassRoom.classNameRu, "
	    + "ClassRoom.numberOfPlaces, ClassRoom.price, "
	    + "ClassRoom.descriptionRu, Answer.price "
	    + "from Answer inner join Users on Answer.login = Users.login "
	    + "join Rooms on Answer.idRoom = Rooms.idRoom "
	    + "join ClassRoom on Rooms.idClass = ClassRoom.idClass "
	    + "join StatusRoom "
	    + "on Rooms.idStatusRoom = StatusRoom.idStatusRoom "
	    + "where Answer.login = ?";

    /**
     * The query gets answers in english language.
     */
    private static final String GET_ANSWERS_EN = "select idAnswer, Answer.idRoom, "
	    + "arrivalDate, dateOfDeparture, numberOfRoom, StatusRoomEn, "
	    + "ClassRoom.idClass,  ClassRoom.classNameEn, "
	    + "ClassRoom.numberOfPlaces, ClassRoom.price, "
	    + "ClassRoom.descriptionEn, Answer.price "
	    + "from Answer inner join Users on Answer.login = Users.login "
	    + "join Rooms on Answer.idRoom = Rooms.idRoom "
	    + "join ClassRoom on Rooms.idClass = ClassRoom.idClass "
	    + "join StatusRoom "
	    + "on Rooms.idStatusRoom = StatusRoom.idStatusRoom "
	    + "where Answer.login = ?";

    /**
     * The query deletes answer.
     */
    private static final String DELETE_ANSWER = "delete from Answer where idAnswer = ?";

    /**
     * The method adds new answer in data base.
     * 
     * @param idRoom
     *            the id of room.
     * @param login
     *            the user login
     * @param price
     *            the common price.
     * @param arrivalDate
     *            the date of arrival
     * @param depDate
     *            the date of depatures
     * @param connection
     *            the connection with data base
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final void add(final Integer idRoom, final String login,
	    final Double price, final String arrivalDate, final String depDate,
	    final Connection connection) throws SQLException {
	try (PreparedStatement ps = connection
		.prepareStatement(INSERT_ANSWER)) {
	    int index = 1;
	    ps.setInt(index++, idRoom);
	    ps.setString(index++, login);
	    ps.setDouble(index++, price);
	    ps.setString(index++, arrivalDate);
	    ps.setString(index++, depDate);
	    ps.executeUpdate();
	}
    }

    /**
     * Deletes answer by id.
     * 
     * @param id
     *            the answer id.
     * @param connection
     *            the connection with data base.
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final void delete(final Integer id, final Connection connection)
	    throws SQLException {
	try (PreparedStatement ps = connection
		.prepareStatement(DELETE_ANSWER)) {
	    ps.setInt(1, id);
	    ps.executeUpdate();
	}
    }

    /**
     * Return list with answers.
     * 
     * @param login
     *            the user login
     * @param language
     *            the language
     * @param connection
     *            the connection with data base.
     * @return list with answers
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    public final List<Answer> getAnswers(final String login,
	    final Language language, final Connection connection)
	    throws SQLException {
	String query = "";
	switch (language) {
	case EN:
	    query = GET_ANSWERS_EN;
	    break;
	default:
	    query = GET_ANSWERS_RU;
	    break;
	}
	List<Answer> answers = new ArrayList<>();
	try (PreparedStatement ps = connection.prepareStatement(query)) {
	    ps.setString(1, login);
	    ResultSet result = ps.executeQuery();
	    while (result.next()) {
		answers.add(createAnswer(result));
	    }
	}
	return answers;
    }

    /**
     * Creates the answer by {@link ResultSet}.
     * 
     * @param result
     *            the object of {@link ResultSet}
     * @return the answer
     * @throws SQLException
     *             an exception that provides information on a
     *             database access error or other errors
     */
    private Answer createAnswer(final ResultSet result) throws SQLException {
	int index = 1;
	Integer id = result.getInt(index++);
	Integer idRoom = result.getInt(index++);
	String arrival = result.getString(index++);
	String depature = result.getString(index++);
	Integer roomNumber = result.getInt(index++);
	String roomStatus = result.getString(index++);
	Integer idClass = result.getInt(index++);
	String className = result.getString(index++);
	Integer placesNumber = result.getInt(index++);
	Double priceRoom = result.getDouble(index++);
	String description = result.getString(index++);
	Double answerPrice = result.getDouble(index++);
	ClassRoom classRoom = new ClassRoom(idClass, className, placesNumber,
		priceRoom, description);
	Room room = new Room(idRoom, roomNumber, roomStatus, classRoom);
	return new Answer(id, room, arrival, depature, answerPrice);
    }

}
