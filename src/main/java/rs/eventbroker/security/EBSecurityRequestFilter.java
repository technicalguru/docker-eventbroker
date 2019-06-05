/**
 * 
 */
package rs.eventbroker.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import rs.eventbroker.Main;

/**
 * Replaces the {@link javax.ws.rs.core.SecurityContext} in the request.
 * @author ralph
 *
 */
@Priority(Priorities.AUTHENTICATION)
@Provider
public class EBSecurityRequestFilter implements ContainerRequestFilter {

	/**
	 * Constructor.
	 */
	public EBSecurityRequestFilter() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Create the context
		EBSecurityContext securityContext = createSecurityContext();
		securityContext.setSecure(requestContext.getSecurityContext().isSecure());
		// Check request request
		String secureToken = Main.getSecureToken();
		if (secureToken == null) {
			securityContext.addRole(EBRoles.CLIENT.name());
		} else {
			String authValue = requestContext.getHeaderString("Authorization");
			if (authValue != null) {
				if (authValue.startsWith("Bearer "+secureToken)) {
					securityContext.addRole(EBRoles.CLIENT.name());
				}
			}
		}
		requestContext.setSecurityContext(securityContext);
	}

	/**
	 * Creates a new security context.
	 * @return a new instance
	 */
	protected EBSecurityContext createSecurityContext() {
		return new EBSecurityContext();
	}

}
