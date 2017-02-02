package ua.nure.shostalo.SummaryTask4.services;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.nure.shostalo.SummaryTask4.repository.exception.RepositoryException;

/**
 * The transaction manager.
 * 
 * @author Mikhail Shostalo
 *
 */
public class TransactionManager {

    private static final Logger logger = LogManager
	    .getLogger(TransactionManager.class.getName());

    /**
     * @see DataSource.
     */
    private DataSource dataSource;

    /**
     * @param dataSource
     *            a data source
     */
    public TransactionManager(final DataSource dataSource) {
	this.dataSource = dataSource;
    }

    /**
     * @param <T>
     * @param operation
     *            {@link TransactionOperation}
     * @return a result of operation
     */
    public final <T> T doInTransaction(
	    final TransactionOperation<T> operation) {
	Connection connection = null;
	try {
	    connection = dataSource.getConnection();
	    connection.setAutoCommit(false);
	    connection.setTransactionIsolation(
		    Connection.TRANSACTION_READ_COMMITTED);
	    T result = operation.operation(connection);
	    connection.commit();
	    return result;
	} catch (SQLException e) {
	    try {
		if (connection != null) {
		    connection.rollback();
		}
	    } catch (SQLException ex) {
		logger.error(ex);
	    }
	    throw new RepositoryException(e);
	} finally {
	    if (connection != null) {
		try {
		    connection.close();
		} catch (SQLException e) {
		    logger.error(e);
		}
	    }
	}
    }

}