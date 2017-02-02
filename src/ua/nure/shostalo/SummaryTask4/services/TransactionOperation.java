package ua.nure.shostalo.SummaryTask4.services;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @param <E>
 * @author Mikhail Shostalo
 *
 */
public interface TransactionOperation<E> {

    /**
     * @param connection
     *            {@link Connection}
     * @return parameterized object
     * @throws SQLException
     *             An exception that provides information on a
     *             database access error or other errors.
     */
    E operation(Connection connection) throws SQLException;
}
