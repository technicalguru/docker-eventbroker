/**
 * 
 */
package rs.eventbroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rs.data.api.IDaoFactory;
import rs.data.impl.OsgiModelServiceImpl;
import rs.eventbroker.db.EBDaoFactory;

/**
 * Ease the testing.
 * @author ralph
 *
 */
public class AbstractTest {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * Constructor
	 */
	public AbstractTest() {
	}

	/**
	 * Returns the logger object.
	 * @return the logger
	 */
	protected Logger getLog() {
		if (log == null) {
			log = LoggerFactory.getLogger(getClass());
		}
		return log;
	}

	/**
	 * Logs the error.
	 * @param message message to log
	 * @param t exception to log
	 */
	public void logError(String message, Throwable t) {
		t.printStackTrace();
		log.error(message, t);
	}

	/**
	 * Logs the error.
	 * @param message message to log
	 */
	public void logError(String message) {
		log.error(message);
	}

	/**
	 * Logs the info message.
	 * @param message message to log
	 */
	public void logInfo(String message) {
		log.info(message);
	}

	/**
	 * Returns the service factory.
	 * @return service factory.
	 */
	public EBDaoFactory getServiceFactory() {
		return OsgiModelServiceImpl.getModelService().getFactory(EBDaoFactory.class);
	}

	/**
	 * Begins a transaction.
	 * @see IDaoFactory#begin()
	 */
	public void begin() {
		getServiceFactory().begin();
	}

	/**
	 * Begins a transaction.
	 * @param timeout timeout of transaction
	 * @see IDaoFactory#begin(long))
	 */
	public void begin(long timeout) {
		getServiceFactory().begin(timeout);
	}

	/**
	 * Commits a transaction.
	 * @see IDaoFactory#commit()
	 */
	public void commit() {
		getServiceFactory().commit();
	}

	/**
	 * Rolls a transaction back.
	 * @see IDaoFactory#rollback()
	 */
	public void rollback() {
		getServiceFactory().rollback();
	}


}
