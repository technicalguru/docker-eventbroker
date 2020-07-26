/**
 * 
 */
package rs.eventbroker.queue;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import rs.eventbroker.queue.EventHandler;

/**
 * Test the topic matching matching.
 * @author ralph
 *
 */
public class EventHandlerTest {

	@Test
	public void testExactMatch() {
		String filter = "topic1/topic2/topic3/topic4/topic5/topic6";
		String topic  = "topic1/topic2/topic3/topic4/topic5/topic6";
		assertTrue("exact matching failed", EventHandler.topicMatches(filter, topic));
	}

	@Test
	public void testExactNoMatch1() {
		String filter = "topic1/topic2/topic3/topic4/topic5/topic6";
		String topic  = "topic1/topic2/topic3/topic41/topic5/topic6";
		assertFalse("exact matching failed", EventHandler.topicMatches(filter, topic));
	}

	@Test
	public void testExactNoMatch2() {
		String filter = "topic1/topic2/topic3/topic4/topic5/topic6";
		String topic  = "topic1/topic2/topic3/topic41/topic5";
		assertFalse("exact matching failed", EventHandler.topicMatches(filter, topic));
	}

	@Test
	public void testPlusMatch() {
		String filter = "topic1/topic2/+/topic4/topic5/topic6";
		String topic  = "topic1/topic2/topic3/topic4/topic5/topic6";
		assertTrue("exact matching failed", EventHandler.topicMatches(filter, topic));
	}

	@Test
	public void testPlusNoMatch1() {
		String filter = "topic1/topic2/+/topic4/topic5/topic6";
		String topic  = "topic1/topic2/topic3/topic4/topic5";
		assertFalse("plus matching failed", EventHandler.topicMatches(filter, topic));
	}

	@Test
	public void testPlusNoMatch2() {
		String filter = "topic1/topic2/+/topic4/topic5/topic6";
		String topic  = "topic1/topic2/topic3/topic41/topic5/topic6";
		assertFalse("plus matching failed", EventHandler.topicMatches(filter, topic));
	}

	@Test
	public void testPlusNoMatch3() {
		String filter = "topic1/topic2/+/topic4/topic5/topic6";
		String topic  = "topic0/topic2/topic3/topic4/topic5/topic6";
		assertFalse("plus matching failed", EventHandler.topicMatches(filter, topic));
	}

	@Test
	public void testHashMatch() {
		String filter = "topic1/topic2/topic3/topic4/#";
		String topic  = "topic1/topic2/topic3/topic4/topic5/topic6";
		assertTrue("hash matching failed", EventHandler.topicMatches(filter, topic));
	}

	@Test
	public void testHashNoMatch1() {
		String filter = "topic1/topic2/topic3/topic4/#";
		String topic  = "topic1/topic2/topic3/topic3/topic5/topic6";
		assertFalse("hash matching failed", EventHandler.topicMatches(filter, topic));
	}

	@Test
	public void testHashNoMatch2() {
		String filter = "topic1/topic2/topic3/topic4/#";
		String topic  = "topic0/topic2/topic3/topic3/topic5/topic6";
		assertFalse("hash matching failed", EventHandler.topicMatches(filter, topic));
	}

	@Test
	public void testInvalidHash() {
		String filter = "topic1/topic2/topic3/topic4/#/Topic6";
		String topic  = "topic1/topic2/topic3/topic4/topic5/topic6";
		assertFalse("hash matching failed", EventHandler.topicMatches(filter, topic));
	}

}
