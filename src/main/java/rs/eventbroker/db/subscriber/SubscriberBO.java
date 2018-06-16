/**
 * 
 */
package rs.eventbroker.db.subscriber;

import rs.data.hibernate.bo.AbstractPlainHibernateBO;

/**
 * Provides the subscriber information.
 * @author ralph
 *
 */
public class SubscriberBO extends AbstractPlainHibernateBO<Integer> implements ISubscriberBO {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;

	private String topic;
	private String url;
	private String authorization;

	/**
	 * Constructor.
	 */
	public SubscriberBO() {
	}

	/**
	 * Returns the subscription topic filter.
	 * @return the topic filter
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Sets the subscription topic filter.
	 * @param topic - the topic filter to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * Returns the callback URL of the client.
	 * @return the callback URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the callback URL of the client.
	 * @param url - the callback URL to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Returns the authorization key to be used at client callback (if any).
	 * @return the authorization key
	 */
	public String getAuthorization() {
		return authorization;
	}

	/**
	 * Sets the authorization key to be used at client callback (if any).
	 * @param authorization - the authorization key to set
	 */
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	
}
