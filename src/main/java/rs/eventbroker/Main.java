package rs.eventbroker;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.LoggerFactory;

import rs.eventbroker.queue.EventBroker;
import rs.eventbroker.security.SecurityRequestFilter;

/**
 * Main class.
 *
 */
public class Main {

	/** Base URI the Grizzly HTTP server will listen on */
	public static String BASE_URI = null;
	/** The token required to authorize */
	protected static String secureToken = null;

	/**
	 * Returns the secure token
	 * @return the secureToken
	 */
	public static String getSecureToken() {
		return secureToken;
	}

	/**
	 * Sets the secure token programmatically.
	 * @param secureToken the secureToken to set
	 */
	public static void setSecureToken(String secureToken) {
		Main.secureToken = secureToken;
	}

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {
		return startServer("0.0.0.0", 80);
	}

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
	 * @param host - the IP address to listen at ({@code null} will listen to all IP addresses)
	 * @param port - the port to listen at {@code 0} will listen to port 80)
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer(String host, int port) {
		// Set the security token from environment
		setSecureToken(System.getenv("EB_SECURE_TOKEN"));

		if (host == null) host = "0.0.0.0";
		if (port == 0) port = 80;
		BASE_URI = "http://"+host+":"+port+"/";

		// create a resource config that scans for JAX-RS resources and providers
		// in com.example package
		ResourceConfig rc = new ResourceConfig().packages("rs.eventbroker");
		rc.register(RolesAllowedDynamicFeature.class);
		rc.register(SecurityRequestFilter.class);
		// create and start a new instance of grizzly http server
		// exposing the Jersey application at given interface hosts
		URI uri = URI.create(BASE_URI);

		// Start server
		LoggerFactory.getLogger(Main.class).info("Listening on: "+uri.toASCIIString());
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, rc);
		
		// Make sure the event broker started
		EventBroker.INSTANCE.start();

		return server;
	}

	/**
	 * Main method.
	 * @param args - command line arguments
	 * @throws IOException - when an exception occured
	 */
	public static void main(String[] args) throws IOException {
		try {
			int port = 80;
			String host = "0.0.0.0";
			if (args.length > 0) {
				port = Integer.parseInt(args[0]);
			}
			if (args.length > 1) {
				host = args[1];
			}
			startServer(host, port);
			System.out.println(String.format("Jersey app started with WADL available at "
					+ "%sapplication.wadl", BASE_URI));
		} catch (Throwable t) {
			LoggerFactory.getLogger(Main.class).error(t.getMessage(), t);
		}
	}
}
