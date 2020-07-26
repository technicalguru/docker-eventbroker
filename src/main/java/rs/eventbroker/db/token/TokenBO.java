/**
 * 
 */
package rs.eventbroker.db.token;

import rs.baselib.util.RsDate;
import rs.data.hibernate.bo.AbstractPlainHibernateBO;

/**
 * Provides the token information.
 * @author ralph
 *
 */
public class TokenBO extends AbstractPlainHibernateBO<Integer> implements ITokenBO {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;

	private String token;
	private RsDate expiryDate;
	private String permissions;

	/**
	 * Constructor.
	 */
	public TokenBO() {
	}

	/**
	 * Returns the token.
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 * @param token - the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Returns the expiry date of this token.
	 * @return the expiry date of this token
	 */
	public RsDate getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Sets the expiry date of this token.
	 * @param expiryDate - the expiry date of this token to set
	 */
	public void setExpiryDate(RsDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Returns the permissions of this token.
	 * <p>The permissions are a comma separated list of additive permission descriptions:</p>
	 * <pre>permission-1,permission-2,...</pre>
	 * <p>Each permission description can occur in two forms:</p>
	 * <ol>
	 * <li><code>api-path</code></li>
	 * <li><code>api-path[allowed-methods]</code></li>
	 * </ol>
	 * <p>{@code api-path} is the path to the REST service that this item describes, e.g. {@code /subscribe}.</p>
	 * <p>{@code allowed-methods} is a space-separated list of HTTP methods that are allowed, e.g. {@code GET POST}. 
	 * If the methods are omitted then all methods are permitted for the given API path.</p>
	 * <p>API paths can use "*" (asterisk) to denote a wildcard for any characters. It is the only wildcard character permitted.</p>
	 * <p>Examples (with description):</p>
	 * <ul>
	 * <li>{@code /subscribe} - Allows any HTTP method on subscribe REST interface.</li>
	 * <li>{@code /unsubscribe[GET POST]} - Allows GET and POST requests only to unsubscribe REST interface.</li>
	 * <li>{@code /* [GET]} - Allows only GET requests to all REST interfaces.</li>
	 * <li>{@code /subscribe[POST],/publish} - Allows a POST request to subscribe REST interface and any request to publish interface.</li>
	 * <li>{@code /subscribe[POST],/*} - Allows all requests to any REST interface.</li>
	 * </ul>
	 * <p>Please notice the last example. It demonstrates that you can overwrite restrictions easily when not careful.</p>
	 * @return the permission description
	 */
	public String getPermissions() {
		return permissions;
	}

	/**
	 * Sets the permissions of this token.
	 * @param permissions - the permissions to set
	 * @see #getPermissions()
	 */
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
}
