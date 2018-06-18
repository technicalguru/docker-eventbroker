/**
 * 
 */
package rs.eventbroker.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rs.baselib.util.CommonUtils;
import rs.eventbroker.db.subscriber.ISubscriberBO;
import rs.eventbroker.db.subscriber.SubscriberDao;
import rs.eventbroker.queue.EventBroker;
import rs.eventbroker.rest.AbstractService;
import rs.eventbroker.rest.RestResult;

/**
 * The actual REST service.
 * @author ralph
 *
 */
@Path("")
@PermitAll
public class EBBrokerService extends AbstractService {

	/** File to be used for testing the service */
	public static final String TEST_FILE       = "test-eventbroker.txt";
	/** Topic to be used for testing the service */
	public static final String TEST_TOPIC      = "test/topic1";
	/** Packet ID to be used for testing the service */
	public static final String TEST_PACKET_ID  = "000001";
	/** Payload to be used for testing the service */
	public static final String TEST_PAYLOAD    = "Hello World!";

	/**
	 * Constructor.
	 */
	public EBBrokerService() {
	}

	/**
	 * Subscribes to topics.
	 * @param event - the event data
	 * @return the subscription data registered
	 */
	@POST
	@Path("publish")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResult<PublishResultData> publish(EventData event) {
		RestResult<PublishResultData> rc = new RestResult<PublishResultData>(new PublishResultData());
		getLog().info("PUBLISH: "+event.toString());
		EventBroker.INSTANCE.publish(event);
		rc.setSuccess(true);
		if (!rc.isSuccess()) rc.setErrorMessage("Cannot enqueue event");
		return rc;
	}

	/**
	 * Consumes an event for testing purposes.
	 * @param event - the event data
	 * @return the event itself
	 */
	@POST
	@Path("consume")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResult<EventData> consume(EventData event) {
		// Signal success to test when the test event is detected
		if (TEST_PACKET_ID.equals(event.getPacketId()) && TEST_TOPIC.equals(event.getTopicName()) && TEST_PAYLOAD.equals(event.getPayload())) {
			File file = new File(TEST_FILE);
			file.deleteOnExit();
			try {
				CommonUtils.writeContent(file, event.toString());
			} catch (Throwable t) {
				getLog().error("Cannot signal test event", t);
			}
		}
		return new RestResult<EventData>(event);
	}

	/**
	 * Subscribes to topics.
	 * @param data - the subscription data
	 * @return the subscription data registered
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("subscribe")
	public RestResult<SubscribeResultData> subscribe(SubscribeData data) {
		SubscribeResultData rc = new SubscribeResultData(data.getPacketId());
		try {
			begin();
			String url  = data.getCallbackUrl();
			String auth = data.getAuthorization();
			List<Integer> returnCodes = new ArrayList<>();
			SubscriberDao dao = getServiceFactory().getSubscriberDao();
			for (String topic : data.getTopics()) {
				ISubscriberBO bo = dao.newInstance();
				bo.setTopic(topic);
				bo.setUrl(url);
				bo.setAuthorization(auth);
				dao.create(bo);
				returnCodes.add(1);
				// TODO Queue retained events for this client
			}
			rc.setReturnCodes(returnCodes);
			commit();
		} catch (Throwable t) {
			try {
				logError("Cannot subscribe", t);
				rollback();
			} catch (Throwable t2) {
				logError("Cannot rollback after error", t2);
			}
			return new RestResult<SubscribeResultData>(false, "Cannot subscribe", rc);
		}
		return new RestResult<SubscribeResultData>(rc);
	}

	/**
	 * Unsubscribes from topics.
	 * @param data - the subscription data
	 * @return the subscription data removed
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("unsubscribe")
	public RestResult<UnsubscribeResultData> unsubscribe(UnsubscribeData data) {
		UnsubscribeResultData rc = new UnsubscribeResultData(data.getPacketId());
		try {
			begin();
			String url  = data.getCallbackUrl();
			SubscriberDao dao = getServiceFactory().getSubscriberDao();
			for (String topic : data.getTopics()) {
				ISubscriberBO bo = dao.findBy(topic, url);
				if (bo != null) {
					dao.delete(bo);
				}
			}
			commit();
		} catch (Throwable t) {
			try {
				logError("Cannot subscribe", t);
				rollback();
			} catch (Throwable t2) {
				logError("Cannot rollback after error", t2);
			}
			return new RestResult<UnsubscribeResultData>(false, "Cannot unsubscribe", rc);
		}
		return new RestResult<UnsubscribeResultData>(rc);
	}


}
