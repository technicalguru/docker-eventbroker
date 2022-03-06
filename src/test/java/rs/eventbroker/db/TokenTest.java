/**
 * 
 */
package rs.eventbroker.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import rs.baselib.util.RsDate;
import rs.eventbroker.AbstractTest;
import rs.eventbroker.db.token.ITokenBO;
import rs.eventbroker.db.token.TokenDao;

/**
 * Test the token data persistence.
 * @author ralph
 *
 */
public class TokenTest extends AbstractTest {


	@Test
	public void testInsert() {
		begin();
		TokenDao dao = getServiceFactory().getTokenDao();
		
		// Create the object
		ITokenBO o = createTestObject(1);
		dao.create(o);
		
		// Search this object
		ITokenBO test = dao.findByToken(o.getToken());
		
		// Test the object
		assertNotNull("Cannot find object that was previously created", test);
		assertEquality(o, test);
		commit();
	}
	
	/**
	 * Asserts that expected and actual object are identical
	 * @param expected - the expected object
	 * @param actual - the actual object
	 */
	protected void assertEquality(ITokenBO expected, ITokenBO actual) {
		assertEquals("Token does not match", expected.getToken(), actual.getToken());
		assertEquals("ExpiryDate does not match", expected.getExpiryDate(), actual.getExpiryDate());
		assertEquals("Permissions do not match", expected.getPermissions(), actual.getPermissions());
	}
	
	/**
	 * Creates a test object
	 * @param version - the version to be produced
	 * @return the test object
	 */
	protected ITokenBO createTestObject(int version) {
		ITokenBO rc = getServiceFactory().getTokenDao().newInstance();
		rc.setToken("testToken-"+version);
		rc.setExpiryDate(new RsDate(System.currentTimeMillis()+DateUtils.MILLIS_PER_DAY));
		rc.setPermissions("/permissions"+version+"[GET]");
		return rc;
	}
}
