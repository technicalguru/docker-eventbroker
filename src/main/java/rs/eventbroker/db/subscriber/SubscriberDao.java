/**
 * 
 */
package rs.eventbroker.db.subscriber;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import rs.data.hibernate.dao.AbstractPlainHibernateDAO;

/**
 * DAO for subscription objects.
 * @author ralph
 *
 */
public class SubscriberDao extends AbstractPlainHibernateDAO<Integer, SubscriberBO, ISubscriberBO> {

	/**
	 * Finds a record by a topic filter and the url.
	 * @param topic - the subscribing filter
	 * @param url - the callback URL
	 * @return the record matching this criteria
	 */
	public ISubscriberBO findBy(String topic, String url) {
		Criterion crit1 = Restrictions.eq("topic", topic);
		Criterion crit2 = Restrictions.eq("url", url);
		return findSingleByCriteria(buildCriteria(crit1, crit2));
	}
}
