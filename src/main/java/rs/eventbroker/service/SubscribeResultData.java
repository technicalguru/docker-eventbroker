/**
 * 
 */
package rs.eventbroker.service;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The object that is returned from subscribe.
 * @author ralph
 *
 */
@XmlRootElement
public class SubscribeResultData implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;

	/** The UID of this event */
	private String packetId;
	/** The topic filters to subscribe */
	private List<Integer> returnCodes;
	
	/**
	 * Constructor.
	 */
	public SubscribeResultData() {
	}

	/**
	 * Constructor.
	 * @param packetId - the packet ID to be used in reference
	 */
	public SubscribeResultData(String packetId) {
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

	/**
	 * @return the returnCodes
	 */
	public List<Integer> getReturnCodes() {
		return returnCodes;
	}

	/**
	 * @param returnCodes the returnCodes to set
	 */
	public void setReturnCodes(List<Integer> returnCodes) {
		this.returnCodes = returnCodes;
	}
	
}
