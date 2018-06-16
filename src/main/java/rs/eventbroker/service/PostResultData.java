/**
 * 
 */
package rs.eventbroker.service;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Result of posting an event.
 * @author ralph
 *
 */
@XmlRootElement
public class PostResultData implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default Constructor.
	 */
	public PostResultData() {
	}

}
