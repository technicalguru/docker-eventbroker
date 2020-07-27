/**
 * 
 */
package rs.eventbroker.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

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
		EBSecurityContext securityContext = createSecurityContext(requestContext);
		requestContext.setSecurityContext(securityContext);
	}

	/**
	 * Creates a new security context.
	 * @param requestContext - the request context for extracting values
	 * @return a new security instance
	 */
	protected EBSecurityContext createSecurityContext(ContainerRequestContext requestContext) {
		String authValue     = requestContext.getHeaderString("Authorization");
		UriInfo uriInfo      = requestContext.getUriInfo();
		EBSecurityContext rc = new EBSecurityContext(uriInfo, authValue);
		rc.setSecure(requestContext.getSecurityContext().isSecure());
		return rc;
	}

}
