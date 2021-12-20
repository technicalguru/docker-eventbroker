/**
 * 
 */
package rs.eventbroker.service.token;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import rs.eventbroker.rest.AbstractService;
import rs.eventbroker.rest.RestResult;

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

	/**
	 * Creates a new token.
	 * @param token - the token data
	 * @return the token data registered
	 */
	@POST
	@RolesAllowed("ADMIN")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Create authorization token",
               description = "Publish an MQTT event on a topic")
	public RestResult<TokenResultData> create(TokenData token) {
		RestResult<TokenResultData> rc = new RestResult<TokenResultData>(new TokenResultData());
		getLog().info("NEW TOKEN: "+token.toString());
		
		rc.setSuccess(true);
		if (!rc.isSuccess()) rc.setErrorMessage("Cannot create token");
		return rc;
	}

}
