/**
 * 
 */
package rs.eventbroker.db;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import rs.baselib.io.FileFinder;
import rs.baselib.util.Environment;
import rs.baselib.util.IUrlProvider;

/**
 * Selects the correct DB file configuration based on the environment.
 * @author ralph
 *
 */
public class EnvironmentUrlProvider implements IUrlProvider {

	public static final IUrlProvider INSTANCE = new Selector();
	public static final String DEFAULT_ENV = "DEFAULT";
		
	/**
	 * Constructor.
	 */
	public EnvironmentUrlProvider() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URL getUrl() {
		return INSTANCE.getUrl();
	}

	/** Internal static selector class so we only lookup the config file once. */
	private static class Selector implements IUrlProvider {
		
		private Map<String, String> envFiles = new HashMap<String, String>();
		
		private URL selectedFile = null;
		
		private Selector() {
			envFiles.put(Environment.PROD.name(), "dbconfig-prod.xml");
			envFiles.put(Environment.TEST.name(), "dbconfig-test.xml");
			envFiles.put(DEFAULT_ENV, "dbconfig.xml");
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public URL getUrl() {
			if (selectedFile == null) {
				String env = null;
				try {
					env = System.getenv("EB_RUNTIME_ENVIRONMENT");
				} catch (SecurityException e) {
					// Ignore, we don't have permission to see this
				}
				// Default is production
				if (env == null) env = DEFAULT_ENV;
				LoggerFactory.getLogger(Environment.class).info("Environment set to "+env);
				selectedFile = FileFinder.find(envFiles.get(env));
				LoggerFactory.getLogger(EBDaoFactory.class).info("Config file: "+selectedFile.toString());
			}
			return selectedFile;
		}
	}
}
