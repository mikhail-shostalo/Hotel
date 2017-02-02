package ua.nure.shostalo.SummaryTask4.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ua.nure.shostalo.SummaryTask4.entity.Answer;
import ua.nure.shostalo.SummaryTask4.entity.Language;
import ua.nure.shostalo.SummaryTask4.repository.AnswerRepository;

/**
 * The service for answer.
 * 
 * @author Mikhail Shostalo
 *
 */
public class AnswerService {

    /**
     * The object of {@link TransactionManager}.
     */
    private TransactionManager transactionManager;

    /**
     * The object of {@link AnswerRepository}.
     */
    private AnswerRepository answerRepository;

    /**
     * Creates answer service.
     * 
     * @param transactionManager
     *            {@link TransactionManager}
     * @param answerRepository
     *            the repository
     */
    public AnswerService(final TransactionManager transactionManager,
	    final AnswerRepository answerRepository) {
	this.transactionManager = transactionManager;
	this.answerRepository = answerRepository;
    }

    /**
     * The service adds new answer in data base.
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
     *            the date of depature.
     */
    public final void add(final Integer idRoom, final String login,
	    final Double price, final String arrivalDate,
	    final String depDate) {
	transactionManager.doInTransaction(new TransactionOperation<Void>() {

	    @Override
	    public Void operation(final Connection connection)
		    throws SQLException {
		answerRepository.add(idRoom, login, price, arrivalDate, depDate,
			connection);
		return null;
	    }
	});
    }

    /**
     * Deletes answer by id.
     * 
     * @param id
     *            the answer id.
     */
    public final void delete(final Integer id) {
	transactionManager.doInTransaction(new TransactionOperation<Void>() {
	    @Override
	    public Void operation(final Connection connection)
		    throws SQLException {
		answerRepository.delete(id, connection);
		return null;
	    }
	});
    }

    /**
     * Return list with answers.
     * 
     * @param login
     *            the user login
     * @param language
     *            the language
     * @return list with answers
     */
    public final List<Answer> getAnswers(final String login,
	    final Language language) {
	return transactionManager
		.doInTransaction(new TransactionOperation<List<Answer>>() {
		    @Override
		    public List<Answer> operation(final Connection connection)
			    throws SQLException {
			return answerRepository.getAnswers(login, language,
				connection);
		    }
		});
    }
}
