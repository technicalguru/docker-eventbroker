/**
 * 
 */
package rs.eventbroker.security;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import rs.baselib.util.CommonUtils;
import rs.eventbroker.Main;

/**
 * Security context for Event Broker.
 * @author ralph
 *
 */
public class EBSecurityContext implements SecurityContext {

	private boolean secure = false;
	private Set<String> roles = new HashSet<String>();
	
	/**
	 * Constructor.
	 * @param uriInfo       - information about the request path
	 * @param authorization - the request authorization header
	 */
	public EBSecurityContext(UriInfo uriInfo, String authorization) {
		String secureToken   = Main.getSecureToken();
		// Check request request
		if (secureToken == null) {
			addRole(EBRoles.CLIENT.name());
		} else {
			if (authorization != null) {
				if (authorization.startsWith("Bearer "+secureToken)) {
					addRole(EBRoles.CLIENT.name());
				}
			}
		}
	}

	/**
	 * Add the given role.
	 * @param name - name of the role to be added
	 */
	public void addRole(String name) {
		roles.add(name);
	}
	
	/**
	 * Add the given roles (comma-separated.
	 * @param roles - roles to be added
	 */
	public void addRoles(String roles) {
		for (String role : roles.split(",")) {
			this.roles.add(role);
		}
	}
	
	/**
	 * Sets the {@link #roles}.
	 * @param roles - the roles to set
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Principal getUserPrincipal() {
		return new EBUserPrincipal();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUserInRole(String role) {
		// authorized clients are always enabled
		if (roles.contains(EBRoles.CLIENT.name())) return true;
		
		// Is the role available?
		return roles.contains(role);
	}

	/**
	 * Returns comma separated list of roles
	 * @return the roles list
	 */
	public String getRoles() {
		return CommonUtils.join(",", roles.toArray(new String[roles.size()]));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSecure() {
		return secure;
	}

	/**
	 * Sets the secure.
	 * @param secure - the secure to set
	 */
	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAuthenticationScheme() {
		return FORM_AUTH;
	}
	
	public static class EBUserPrincipal implements Principal {

		public EBUserPrincipal() {
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getName() {
			return toString();
		}

	}
}
