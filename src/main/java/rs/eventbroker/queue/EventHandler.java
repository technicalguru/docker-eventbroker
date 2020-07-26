/**
 * 
 */
package rs.eventbroker.queue;

import java.util.concurrent.Executor;

import rs.data.util.IDaoIterator;
import rs.eventbroker.db.subscriber.ISubscriberBO;
import rs.eventbroker.service.mqtt.EventData;

/**
 * This class will take an event from the event queue and match it to 
 * subscribers. The class will fill up the distribution queue.
 * @author ralph
 *
 */
public class EventHandler extends AbstractWorker {

	/** The executor to be used */
	private Executor executor;
	/** The event to distribute */
	private EventData event;

	/**
	 * Constructor.
	 * @param executor - the executor to be used for scheduling event distribution
	 * @param event    - the event to be published
	 */
	public EventHandler(Executor executor, EventData event) {
		this.executor = executor;
		this.event  = event;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		try {
			begin();
			// Match the event to all subscriptions
			// create the distribution events
			IDaoIterator<ISubscriberBO> i = getServiceFactory().getSubscriberDao().iterateAll();
			while (i.hasNext()) {
				ISubscriberBO subscriber = i.next();
				if (topicMatches(subscriber.getTopic(), event.getTopicName())) {
					executor.execute(new PublishingWorker(new ConsumerEventData(event, subscriber))); 
				}
			}
			i.close();
		} finally {
			commit();
		}
	}

	/**
	 * Matches the topic against the filter.
	 * <p>Patterns are:</p>
	 * <ul>
	 * <li>+ - matches a single element (allowed anywhere, e.g. {@code /a/b/+/d})</li> 
	 * <li># - matches multiple elements (allowed at end only, e.g. {@code /a/b/c/#})</li>
	 * </ul>
	 * @param filter - the filter to be used
	 * @param topic  - the topic to be matched
	 * @return {@code true} when the topic matches against the filter
	 */
	public static boolean topicMatches(String filter, String topic) {
		// Exact match
		if (filter.equals(topic)) return true;
		// Split both strings and match per element
		String fElements[] = filter.split("/");
		String tElements[] = topic.split("/");
		for (int i=0; i<Math.min(fElements.length, tElements.length); i++) {
			if (fElements[i].equals("+")) {
				// Skip this element
			} else if (fElements[i].equals("#")) {
				// This must be the last element in the filter
				if (i == fElements.length-1) return true;
				// There is more? Error in filter
				return false;
			} else if (!fElements[i].equals(tElements[i])){
				return false;
			}
		}
		// matches only when both strings have equal number of elements
		return fElements.length == tElements.length;
	}

	/**
	 * Returns the event.
	 * @return the event
	 */
	public EventData getEvent() {
		return event;
	}
	
	
}
