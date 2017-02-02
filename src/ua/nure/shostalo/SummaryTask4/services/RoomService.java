package ua.nure.shostalo.SummaryTask4.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ua.nure.shostalo.SummaryTask4.entity.ClassRoom;
import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.Room;
import ua.nure.shostalo.SummaryTask4.repository.RoomRepository;

/**
 * The service for rooms.
 * 
 * @author Mikhail Shostalo
 *
 */
public class RoomService {

    /**
     * The object of {@link TransactionManager}.
     */
    private TransactionManager transactionManager;

    /**
     * The object of {@link RoomRepository}.
     */
    private RoomRepository roomRepository;

    /**
     * Create a room service.
     * 
     * @param transactionManager
     * @param roomRepository
     */
    public RoomService(final TransactionManager transactionManager,
	    final RoomRepository roomRepository) {
	this.transactionManager = transactionManager;
	this.roomRepository = roomRepository;
    }

    /**
     * Returns all rooms.
     * 
     * @param language
     *            the language.
     * @return list of rooms.
     */
    public final List<Room> getAllRooms(final Language language) {
	return transactionManager
		.doInTransaction(new TransactionOperation<List<Room>>() {
		    @Override
		    public List<Room> operation(final Connection connection)
			    throws SQLException {
			return roomRepository.getRooms(language, connection);
		    }
		});
    }

    /**
     * Gets rooms by number of people.
     * 
     * @param numberOfPlace
     *            the number of people
     * @param language
     *            the language
     */
    public final List<Room> getRoomsByNumberOfPeople(
	    final Integer numberOfPeople, final Language language) {
	return transactionManager
		.doInTransaction(new TransactionOperation<List<Room>>() {
		    @Override
		    public List<Room> operation(final Connection connection)
			    throws SQLException {
			return roomRepository.getRoomsByNumberOfPeople(
				numberOfPeople, language, connection);
		    }
		});
    }

    /**
     * Returns list of room classes.
     * 
     * @param language
     *            the language
     * @return list of room classes
     */
    public final List<ClassRoom> getAllRoomClasses(final Language language) {
	return transactionManager
		.doInTransaction(new TransactionOperation<List<ClassRoom>>() {
		    @Override
		    public List<ClassRoom> operation(
			    final Connection connection) throws SQLException {
			return roomRepository.getRoomsClasses(language,
				connection);
		    }
		});
    }

    /**
     * Returns room by id.
     * 
     * @param id
     *            the room id
     * @param language
     *            the language
     * @return room by id
     */
    public final Room getRoomById(final Integer id, final Language language) {
	return transactionManager
		.doInTransaction(new TransactionOperation<Room>() {
		    @Override
		    public Room operation(final Connection connection)
			    throws SQLException {
			return roomRepository.getRoomById(id, language,
				connection);
		    }
		});
    }

    /**
     * Updates status of room.
     * 
     * @param room
     *            the room
     * @param status
     *            the stat
     */
    public final void updateStatus(final Room room, final String status) {
	transactionManager.doInTransaction(new TransactionOperation<Void>() {
	    @Override
	    public Void operation(final Connection connection)
		    throws SQLException {
		roomRepository.updateStatus(room, status, connection);
		return null;
	    }
	});
    }

    /**
     * Returns the class of room by id.
     * 
     * @param id
     *            the room class id.
     * @return the class of room
     */
    public final ClassRoom getRoomClassById(final Integer id) {
	return transactionManager
		.doInTransaction(new TransactionOperation<ClassRoom>() {
		    @Override
		    public ClassRoom operation(final Connection connection)
			    throws SQLException {
			return roomRepository.getRoomClassbyId(id, connection);
		    }
		});
    }
}
