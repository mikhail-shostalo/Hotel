package ua.nure.shostalo.SummaryTask4.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.entity.Reserve;
import ua.nure.shostalo.SummaryTask4.repository.ReserveRepository;

/**
 * The service for reserve.
 * 
 * @author Mikhail Shostalo
 *
 */
public class ReserveService {

    /**
     * The object of {@link TransactionManager}.
     */
    private TransactionManager transactionManager;

    /**
     * The object of {@link ReserveRepository}.
     */
    private ReserveRepository reserveRepository;

    /**
     * Creates resere service.
     * 
     * @param transactionManager
     *            {@link TransactionManager}
     * @param reserveRepository
     *            the repository
     */
    public ReserveService(final TransactionManager transactionManager,
	    final ReserveRepository reserveRepository) {
	this.transactionManager = transactionManager;
	this.reserveRepository = reserveRepository;
    }

    /**
     * Adds new Reserve.
     * 
     * @param reserve
     *            the new reserve
     * @param login
     *            the user login
     */
    public final void add(final Reserve reserve, final String login) {
	transactionManager.doInTransaction(new TransactionOperation<Void>() {
	    @Override
	    public Void operation(final Connection connection)
		    throws SQLException {
		reserveRepository.add(reserve, login, connection);
		return null;
	    }
	});
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
     * @return list of reserves
     */
    public final List<Reserve> getReserves(final String login,
	    final String status, final Language language) {
	return transactionManager
		.doInTransaction(new TransactionOperation<List<Reserve>>() {
		    @Override
		    public List<Reserve> operation(final Connection connection)
			    throws SQLException {
			return reserveRepository.getReserve(login, status,
				language, connection);
		    }
		});
    }

    /**
     * Deletes reserve by id.
     * 
     * @param id
     *            the id of reserve
     */
    public final void delete(final Integer id) {
	transactionManager.doInTransaction(new TransactionOperation<Void>() {
	    @Override
	    public Void operation(final Connection connection)
		    throws SQLException {
		reserveRepository.delete(id, connection);
		return null;
	    }
	});
    }

    /**
     * Updates status of reserve.
     * 
     * @param status
     *            the status of reserve
     * @param id
     *            the id of status
     */
    public final void updateStatus(final String status, final Integer id) {
	transactionManager.doInTransaction(new TransactionOperation<Void>() {
	    @Override
	    public Void operation(final Connection connection)
		    throws SQLException {
		reserveRepository.updateStatus(status, id, connection);
		return null;
	    }
	});
    }

}
