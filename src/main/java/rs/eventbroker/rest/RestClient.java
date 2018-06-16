/**
 * 
 */
package rs.eventbroker.rest;

import java.util.logging.Level;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import rs.eventbroker.Main;

/**
 * Helper class for performing REST calls.
 * @author ralph
 *
 */
public class RestClient {

	private String secureToken;
	private String baseUri = null;
	private boolean debug = false;
	private Client client = null;
	private WebTarget baseTarget = null;

	/**
	 * Constructor.
	 */
	public RestClient(String baseUri, String secureToken, boolean debug) {
		this.baseUri = baseUri;
		this.secureToken = secureToken;
		this.debug   = debug;
	}

	/**
	 * Returns the {@link #baseUri}.
	 * @return the baseUri
	 */
	public String getBaseUri() {
		return baseUri;
	}

	/**
	 * Returns the {@link #debug}.
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * Creates the client.
	 * @return the client
	 */
	protected Client createClient() {
		Client rc = null;
		if (isDebug()) {
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.HEADERS_ONLY);
			clientConfig.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, Level.INFO.getName());
			rc = ClientBuilder.newClient(clientConfig);
		} else {
			rc = ClientBuilder.newClient();
		}
		return rc;
	}

	/**
	 * Returns the client.
	 * @return the client
	 */
	protected Client getClient() {
		if (client == null) {
			client = createClient();
		}
		return client;
	}

	/**
	 * Creates the base target.
	 * @return the base target
	 */
	protected WebTarget createBaseTarget() {
		return getClient().target(Main.BASE_URI);
	}

	/**
	 * Returns the base target
	 * @return the base target
	 */
	protected WebTarget getBaseTarget() {
		if (baseTarget == null) {
			baseTarget = createBaseTarget();
		}
		return baseTarget;
	}

	/**
	 * Returns a request builder for the path.
	 * @param path the path for the request.
	 * @return the builder
	 */
	public WebTarget getTarget(String path) {
		WebTarget rc = getBaseTarget().path(path);
		return rc;
	}

	/**
	 * Returns a request builder for the path.
	 * @param path the path for the request.
	 * @return the builder
	 */
	public Builder getRequest(WebTarget target) {
		Builder rc = target.request();
		if (secureToken != null) {
			rc.header("Authorization", "Bearer "+secureToken);
		}
		return rc;
	}

	/**
	 * Returns a request builder for the path.
	 * @param path the path for the request.
	 * @return the builder
	 */
	public Builder getRequest(String path) {
		return getRequest(getTarget(path));
	}


}
