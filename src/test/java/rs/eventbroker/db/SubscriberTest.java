/**
 * 
 */
package rs.eventbroker.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import rs.eventbroker.AbstractTest;
import rs.eventbroker.db.subscriber.ISubscriberBO;
import rs.eventbroker.db.subscriber.SubscriberDao;

/**
 * Test the subscriber data persistence.
 * @author ralph
 *
 */
public class SubscriberTest extends AbstractTest {


	@Test
	public void testInsert() {
		begin();
		SubscriberDao dao = getServiceFactory().getSubscriberDao();
		
		// Create the object
		ISubscriberBO o = createTestObject(1);
		dao.create(o);
		
		// Search this object
		ISubscriberBO test = dao.findBy(o.getId());
		
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
	protected void assertEquality(ISubscriberBO expected, ISubscriberBO actual) {
		assertEquals("Topic does not match", expected.getTopic(), actual.getTopic());
		assertEquals("URL does not match", expected.getUrl(), actual.getUrl());
		assertEquals("Authorization does not match", expected.getAuthorization(), actual.getAuthorization());
	}
	
	/**
	 * Creates a test object
	 * @param version - the version to be produced
	 * @return the test object
	 */
	protected ISubscriberBO createTestObject(int version) {
		ISubscriberBO rc = getServiceFactory().getSubscriberDao().newInstance();
		rc.setTopic("test/subscriberTest/"+version);
		rc.setUrl("http://subscriber-test/"+version);
		rc.setAuthorization("authorization"+version);
		return rc;
	}
}
