package ua.nure.shostalo.SummaryTask4.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ua.nure.shostalo.SummaryTask4.entity.Request;
import ua.nure.shostalo.SummaryTask4.repository.RequestRepository;

/**
 * The service for request.
 * 
 * @author Mikhail Shostalo
 *
 */
public class RequestService {

    /**
     * The object of {@link TransactionManager}.
     */
    private TransactionManager transactionManager;

    /**
     * The object of {@link RequestRepository}.
     */
    private RequestRepository requestRepository;

    /**
     * Creates request service.
     * 
     * @param transactionManager
     *            {@link TransactionManager}
     * @param requestRepository
     *            the repository
     */
    public RequestService(final TransactionManager transactionManager,
	    final RequestRepository requestRepository) {
	this.transactionManager = transactionManager;
	this.requestRepository = requestRepository;
    }

    /**
     * The service adds the request.
     * 
     * @param request
     *            the request
     * @param login
     *            the user login
     */
    public final void add(final Request request, final String login) {
	transactionManager.doInTransaction(new TransactionOperation<Void>() {
	    @Override
	    public Void operation(final Connection connection)
		    throws SQLException {
		requestRepository.add(request, login, connection);
		return null;
	    }
	});
    }

    /**
     * Deletes request by id.
     * 
     * @param id
     *            the request id
     */
    public final void delete(final Integer id) {
	transactionManager.doInTransaction(new TransactionOperation<Void>() {
	    @Override
	    public Void operation(final Connection connection)
		    throws SQLException {
		requestRepository.delete(id, connection);
		return null;
	    }
	});
    }

    /**
     * Returns request by id.
     * 
     * @param id
     *            the request id
     * @return request by id
     */
    public final Request getById(final Integer id) {
	return transactionManager
		.doInTransaction(new TransactionOperation<Request>() {
		    @Override
		    public Request operation(final Connection connection)
			    throws SQLException {
			return requestRepository.getById(id, connection);
		    }
		});
    }

    /**
     * Returns request list.
     * 
     * @return request list
     */
    public final List<Request> getRequests() {
	return transactionManager
		.doInTransaction(new TransactionOperation<List<Request>>() {
		    @Override
		    public List<Request> operation(final Connection connection)
			    throws SQLException {
			return requestRepository.getRequests(connection);
		    }
		});
    }
}
