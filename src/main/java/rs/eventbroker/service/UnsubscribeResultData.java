/**
 * 
 */
package rs.eventbroker.service;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The object that is returned from unsubscribe.
 * @author ralph
 *
 */
@XmlRootElement
public class UnsubscribeResultData implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;

	/** The UID of this event */
	private String packetId;
	
	/**
	 * Constructor.
	 */
	public UnsubscribeResultData() {
	}

	/**
	 * Constructor.
	 */
	public UnsubscribeResultData(String packetId) {
		setPacketId(packetId);
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

}
