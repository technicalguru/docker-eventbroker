/**
 * 
 */
package rs.eventbroker.service;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * An event that occurs.
 * @author ralph
 *
 */
@XmlRootElement
public class EventData implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/** The UID of this event */
	private String packetId;
	/** The topic to be POSTed to */
	private String topicName;
	/** Quality of Service (0=at most once, 1=at least once, 2=exactly once) */
	private Integer qos;
	/** The payload of this event */
	private String payload;
	/** Whether message shall be retained for new clients subscribing */
	private boolean retainFlag;
	/** Whether event is a duplicate of an earlier event */
	private boolean dupFlag;
	
	/**
	 * Default Constructor.
	 */
	public EventData() {
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
	 * Returns the topic name to be published to.
	 * @return the topic name
	 */
	public String getTopicName() {
		return topicName;
	}
	
	/**
	 * Sets the topic name to be published to.
	 * @param topicName - the topic name to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	/**
	 * Returns the Quality of Service level (0=at most once, 1=at least once, 2=exactly once).
	 * @return the Quality of Service level
	 */
	public Integer getQos() {
		return qos;
	}
	
	/**
	 * Sets the Quality of Service level (0=at most once, 1=at least once, 2=exactly once).
	 * @param qos - the Quality of Service level to set
	 */
	public void setQos(Integer qos) {
		this.qos = qos;
	}
	
	/**
	 * Returns the payload of the event.
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}
	
	/**
	 * Sets the payload of the event.
	 * @param payload - the payload to set
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	/**
	 * Returns whether the event will be saved and made available to new subscribers.
	 * @return {@code true} when event needs to be retained
	 */
	public boolean isRetainFlag() {
		return retainFlag;
	}
	
	/**
	 * Sets whether the event will be saved and made available to new subscribers.
	 * @param retainFlag - {@code true} when event needs to be retained
	 */
	public void setRetainFlag(boolean retainFlag) {
		this.retainFlag = retainFlag;
	}
	
	/**
	 * Returns whether this is a duplicate event.
	 * @return {@code true} when event is a duplicate
	 */
	public boolean isDupFlag() {
		return dupFlag;
	}
	
	/**
	 * Sets whether this is a duplicate event.
	 * @param dupFlag - {@code true} when event is a duplicate
	 */
	public void setDupFlag(boolean dupFlag) {
		this.dupFlag = dupFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "EventData [packetId=" + packetId + ", topicName=" + topicName + ", qos=" + qos + ", payload=" + payload
				+ ", retainFlag=" + retainFlag + ", dupFlag=" + dupFlag + "]";
	}

	
}
