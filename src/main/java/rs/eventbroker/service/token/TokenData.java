/**
 * 
 */
package rs.eventbroker.service.token;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;

/**
 * @author ralph
 *
 */
@XmlRootElement
@ApiModel(value="Security Token", description="A Security Token")
public class TokenData implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public TokenData() {
	}


}
