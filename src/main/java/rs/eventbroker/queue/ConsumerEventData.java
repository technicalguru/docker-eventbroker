/**
 * 
 */
package rs.eventbroker.queue;

import rs.eventbroker.db.subscriber.ISubscriberBO;
import rs.eventbroker.service.mqtt.EventData;

/**
 * Holds information about a consumer and an event to be delivered.
 * @author ralph
 *
 */
public class ConsumerEventData {

	/** The event to be delivered */
	protected EventData event;
	/** The subscriber of this event */
	protected ISubscriberBO subscriber;

	/**
	 * Constructor.
	 */
	public ConsumerEventData() {
	}

	/**
	 * Constructor.
	 * @param event - event to be delivered
	 * @param subscriber - subscription information
	 */
	public ConsumerEventData(EventData event, ISubscriberBO subscriber) {
		setEvent(event);
		setSubscriber(subscriber);
	}

	/**
	 * Returns the event.
	 * @return the event
	 */
	public EventData getEvent() {
		return event;
	}

	/**
	 * Sets the event.
	 * @param event - the event to set
	 */
	public void setEvent(EventData event) {
		this.event = event;
	}

	/**
	 * Returns the subscriber.
	 * @return the subscriber
	 */
	public ISubscriberBO getSubscriber() {
		return subscriber;
	}

	/**
	 * Sets the subscriber.
	 * @param subscriber - the subscriber to set
	 */
	public void setSubscriber(ISubscriberBO subscriber) {
		this.subscriber = subscriber;
	}


}
