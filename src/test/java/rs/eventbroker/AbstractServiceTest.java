/**
 * 
 */
package rs.eventbroker;

import java.util.logging.Level;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.BackgroundScheduler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import rs.eventbroker.queue.EventBroker;

/**
 * Implements base test methods.
 * @author ralph
 *
 */
public abstract class AbstractServiceTest extends AbstractTest {

	private static final String TEST_SERVER_HOST = "localhost";
	private static final int    TEST_SERVER_PORT = 8088;

	public boolean debug = false;
	private HttpServer server;
	private WebTarget baseTarget;
	private String serviceHost = TEST_SERVER_HOST;
	private int servicePort    = TEST_SERVER_PORT;

	/**
	 * Constructor.
	 * @param debug whether to construct debug.
	 */
	public AbstractServiceTest(boolean debug) {
		this.debug = debug;
	}

	/**
	 * Returns the serviceHost.
	 * @return the serviceHost
	 */
	public String getServiceHost() {
		return serviceHost;
	}


	/**
	 * Returns the servicePort.
	 * @return the servicePort
	 */
	public int getServicePort() {
		return servicePort;
	}


	@Before
	public void setUp() throws Exception {
		server = Main.startServer(getServiceHost(), getServicePort());
		Client c = null;
		if (isDebug()) {
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_TEXT);
			clientConfig.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, Level.INFO.getName());
			c = ClientBuilder.newClient(clientConfig);
		} else {
			c = ClientBuilder.newClient();
		}
		baseTarget = c.target(Main.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
		server.shutdownNow();
	}

	/**
	 * Returns a request builder for the path.
	 * @param path the path for the request.
	 * @return the builder
	 */
	public WebTarget getTarget(String path) {
		return getBaseTarget().path(path);
	}

	/**
	 * Returns a request builder for the path.
	 * @param path the path for the request.
	 * @return the builder
	 */
	public Builder getRequest(String path) {
		return getRequest(path, null);
	}

	/**
	 * Returns a request builder for the path.
	 * @param path the path for the request.
	 * @param internal whether an internal Bearer authentication header shall be set
	 * @return the builder
	 */
	public Builder getRequest(String path, String secureToken) {
		Builder rc = getTarget(path).request();
		if (secureToken != null) {
			rc.header("Authorization", "Bearer "+secureToken);
		}
		return rc;
	}

	/**
	 * Returns the {@link #debug}.
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * Returns the {@link #server}.
	 * @return the server
	 */
	public HttpServer getServer() {
		return server;
	}

	/**
	 * Returns the {@link #baseTarget}.
	 * @return the baseTarget
	 */
	public WebTarget getBaseTarget() {
		return baseTarget;
	}


}
