package ua.nure.shostalo.SummaryTask4.services;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.shostalo.SummaryTask4.entity.User;
import ua.nure.shostalo.SummaryTask4.repository.UserRepository;

/**
 * The service for user.
 * 
 * @author Mikhail Shostalo
 *
 */
public class UserService {

    /**
     * The object of {@link TransactionManager}.
     */
    private TransactionManager transactionManager;

    /**
     * The object of {@link UserRepository}.
     */
    private UserRepository userRepository;

    /**
     * Creates user service.
     * 
     * @param transactionManager
     *            {@link TransactionManager}
     * @param userRepository
     *            the repository
     */
    public UserService(final TransactionManager transactionManager,
	    final UserRepository userRepository) {
	this.transactionManager = transactionManager;
	this.userRepository = userRepository;
    }

    /**
     * The service adds the user.
     * 
     * @param user
     *            the user
     */
    public final void add(final User user) {
	transactionManager.doInTransaction(new TransactionOperation<Void>() {
	    @Override
	    public Void operation(final Connection connection)
		    throws SQLException {
		userRepository.addUser(user, connection);
		return null;
	    }
	});
    }

    /**
     * Returns true if login exists, false - otherwise.
     * 
     * @param login
     *            user login
     * @return true if login exists, false - otherwise
     */
    public final Boolean isLoginUsed(final String login) {
	return transactionManager
		.doInTransaction(new TransactionOperation<Boolean>() {
		    @Override
		    public Boolean operation(final Connection connection)
			    throws SQLException {
			if (userRepository.getUser(login, connection) != null) {
			    return true;
			}
			return false;
		    }
		});
    }

    /**
     * The service gets the user by login and password.
     * 
     * @param login
     *            user login
     * @param password
     *            user password
     * @return the user
     */
    public final User getUser(final String login, final String password) {
	return transactionManager
		.doInTransaction(new TransactionOperation<User>() {
		    @Override
		    public User operation(final Connection connection)
			    throws SQLException {
			User user = userRepository.getUser(login, connection);
			if (user != null && login.equals(user.getLogin())
				&& password.equals(user.getPassword())) {
			    return user;
			}
			return null;
		    }
		});
    }

}
