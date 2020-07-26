/**
 * 
 */
package rs.eventbroker.queue;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rs.eventbroker.rest.RestClient;
import rs.eventbroker.service.mqtt.EventData;

/**
 * This class will take a distribution event and 
 * actually call the subscriber.
 * @author ralph
 *
 */
public class PublishingWorker extends AbstractWorker {

	/** The logger */
	private static Logger log = LoggerFactory.getLogger(EventBroker.class);

	/** the data to process */
	private ConsumerEventData data;
	
	/**
	 * Constructor.
	 * @param data - the data about distribution to be processed
	 */
	public PublishingWorker(ConsumerEventData data) {
		this.data = data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		try {
			// Call the subscriber
			log.debug("POST: "+data.getEvent().toString());
			log.debug("URL: "+data.getSubscriber().getUrl()+(data.getSubscriber().getAuthorization() != null ? " (Authorized)" : ""));
			RestClient client = new RestClient(data.getSubscriber().getUrl(), data.getSubscriber().getAuthorization(), false);
			Entity<EventData> payload = Entity.entity(data.getEvent(), MediaType.APPLICATION_JSON_TYPE);
			String response = client.getRequest("").post(payload, String.class);
			log.debug("RESPONSE: "+response);
		} catch (Throwable t) {
			log.error("Cannot call subscriber", t);
		}
	}
}
