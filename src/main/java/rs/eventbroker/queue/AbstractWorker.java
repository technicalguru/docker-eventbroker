/**
 * 
 */
package rs.eventbroker.queue;

import rs.data.impl.OsgiModelServiceImpl;
import rs.eventbroker.db.EBDaoFactory;

/**
 * Common functionality for the workers-
 * @author ralph
 *
 */
public abstract class AbstractWorker implements Runnable {

	/**
	 * Constructor.
	 */
	public AbstractWorker() {
		
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
	 * @see EBDaoFactory#begin()
	 */
	public void begin() {
		getServiceFactory().begin();
	}

	/**
	 * Begins a transaction.
	 * @param timeout timeout of transaction
	 * @see EBDaoFactory#begin(long)
	 */
	public void begin(long timeout) {
		getServiceFactory().begin(timeout);
	}

	/**
	 * Commits a transaction.
	 * @see EBDaoFactory#commit()
	 */
	public void commit() {
		getServiceFactory().commit();
	}

	/**
	 * Rolls a transaction back.
	 * @see EBDaoFactory#rollback()
	 */
	public void rollback() {
		getServiceFactory().rollback();
	}
}
