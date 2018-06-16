/**
 * 
 */
package rs.eventbroker.db;

import rs.data.impl.AbstractDaoFactory;
import rs.eventbroker.db.subscriber.SubscriberDao;

/**
 * @author ralph
 *
 */
public class EBDaoFactory extends AbstractDaoFactory {

	/**
	 * Constructor.
	 */
	public EBDaoFactory() {
	}

	/**
	 * Returns the subscription DAO.
	 * @return the subscription DAO
	 */
	public SubscriberDao getSubscriberDao() {
		return getDao(SubscriberDao.class);
	}
}
