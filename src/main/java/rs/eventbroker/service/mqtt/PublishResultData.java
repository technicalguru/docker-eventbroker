/**
 * 
 */
package rs.eventbroker.service.mqtt;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Result of posting an event.
 * @author ralph
 *
 */
@XmlRootElement
public class PublishResultData implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default Constructor.
	 */
	public PublishResultData() {
	}

}
