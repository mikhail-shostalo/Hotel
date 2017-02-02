package ua.nure.shostalo.SummaryTask4.repository.exception;

/**
 * @author shost
 *
 */
public class RepositoryException extends RuntimeException {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public RepositoryException() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @param message
     *            exception message
     */
    public RepositoryException(final String message) {
	super(message);
    }

    /**
     * @param cause
     *            the cause (which is saved for later retrieval by the
     *            {@link #getCause()} method).
     */
    public RepositoryException(final Throwable cause) {
	super(cause);
    }

}
