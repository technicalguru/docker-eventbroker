/**
 * 
 */
package rs.eventbroker.db.subscriber;

import rs.data.api.bo.IGeneralBO;

/**
 * Interface for the subscriber information.
 * @author ralph
 *
 */
public interface ISubscriberBO extends IGeneralBO<Integer> {

	/**
	 * Returns the subscription topic filter.
	 * @return the topic filter
	 */
	public String getTopic();

	/**
	 * Sets the subscription topic filter.
	 * @param topic - the topic filter to set
	 */
	public void setTopic(String topic);

	/**
	 * Returns the callback URL of the client.
	 * @return the callback URL
	 */
	public String getUrl();

	/**
	 * Sets the callback URL of the client.
	 * @param url - the callback URL to set
	 */
	public void setUrl(String url);

	/**
	 * Returns the authorization key to be used at client callback (if any).
	 * @return the authorization key
	 */
	public String getAuthorization();

	/**
	 * Sets the authorization key to be used at client callback (if any).
	 * @param authorization - the authorization key to set
	 */
	public void setAuthorization(String authorization);

	
}
