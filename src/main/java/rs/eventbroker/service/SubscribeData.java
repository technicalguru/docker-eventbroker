/**
 * 
 */
package rs.eventbroker.service;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The object that is used as payload for subscribe.
 * @author ralph
 *
 */
@XmlRootElement
public class SubscribeData implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;

	/** The UID of this event */
	private String packetId;
	/** The topic filters to subscribe. */
	private List<String> topics;
	/** The callback URL */
	private String callbackUrl;
	/** The Bearer token to be used with the callback */
	private String authorization;
	
	/**
	 * Constructor.
	 */
	public SubscribeData() {
	}

	/**
	 * Returns the unique event ID.
	 * @return the unique packet id
	 */
	public String getPacketId() {
		return packetId;
	}
	
	/**
	 * Sets the unique event ID.
	 * @param packetId - the unique event ID to set
	 */
	public void setPacketId(String packetId) {
		this.packetId = packetId;
	}
	
	/**
	 * Returns the topic filters to subscribe.
	 * @return the topic filters
	 */
	public List<String> getTopics() {
		return topics;
	}

	/**
	 * Sets the topic filters to subscribe.
	 * @param topics - the topic filters to set
	 */
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	/**
	 * Returns the callback URL to be used when the event occurs.
	 * @return the client's callback URL
	 */
	public String getCallbackUrl() {
		return callbackUrl;
	}

	/**
	 * Sets the callback URL to be used when the event occurs.
	 * @param callbackUrl - the client's callback URL to set
	 */
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	/**
	 * Returns the Bearer token to be used with the callback.
	 * @return the token (or {@code null})
	 */
	public String getAuthorization() {
		return authorization;
	}

	/**
	 * Sets the Bearer token to be used with the callback.
	 * @param authorization - the token to set
	 */
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	
}
