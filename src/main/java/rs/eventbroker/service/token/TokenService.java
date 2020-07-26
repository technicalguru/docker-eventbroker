/**
 * 
 */
package rs.eventbroker.service.token;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Path;

import io.swagger.annotations.Api;
import rs.eventbroker.rest.AbstractService;

/**
 * A maintenance service for tokens.
 * @author ralph
 *
 */
@Path("tokens")
@PermitAll
@Api
public class TokenService extends AbstractService {

	/**
	 * Constructor.
	 */
	public TokenService() {
	}

}
