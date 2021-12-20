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
 * Replaces the {@link javax.ws.rs.core.EbSecurityContext} in the request.
 * @author ralph
 *
 */
@Priority(Priorities.AUTHENTICATION)
@Provider
public class SecurityRequestFilter implements ContainerRequestFilter {

	/**
	 * Constructor.
	 */
	public SecurityRequestFilter() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Create the context
		EbSecurityContext ebSecurityContext = createSecurityContext(requestContext);
		requestContext.setSecurityContext(ebSecurityContext);
	}

	/**
	 * Creates a new security context.
	 * @param requestContext - the request context for extracting values
	 * @return a new security instance
	 */
	protected EbSecurityContext createSecurityContext(ContainerRequestContext requestContext) {
		String authValue     = requestContext.getHeaderString("Authorization");
		UriInfo uriInfo      = requestContext.getUriInfo();
		EbSecurityContext rc = new EbSecurityContext(uriInfo, authValue);
		rc.setSecure(requestContext.getSecurityContext().isSecure());
		return rc;
	}

}
