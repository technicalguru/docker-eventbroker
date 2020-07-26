/**
 * 
 */
package rs.eventbroker.db;

import rs.data.impl.AbstractDaoFactory;
import rs.eventbroker.db.subscriber.SubscriberDao;
import rs.eventbroker.db.token.TokenDao;

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

	/**
	 * Returns the token DAO.
	 * @return the token DAO
	 */
	public TokenDao getTokenDao() {
		return getDao(TokenDao.class);
	}
}
