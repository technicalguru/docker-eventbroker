/**
 * 
 */
package rs.eventbroker.db.token;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import rs.data.hibernate.dao.AbstractPlainHibernateDAO;

/**
 * DAO for subscription objects.
 * @author ralph
 *
 */
public class TokenDao extends AbstractPlainHibernateDAO<Integer, TokenBO, ITokenBO> {

	/**
	 * Finds a token.
	 * @param token - the token to be found
	 * @return the token
	 */
	public ITokenBO findByToken(String token) {
		Criterion crit1 = Restrictions.eq("token", token);
		return findSingleByCriteria(buildCriteria(crit1));
	}
}
