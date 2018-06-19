/**
 * 
 */
package rs.eventbroker.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rs.eventbroker.service.EventData;

/**
 * The broker.
 * @author ralph
 *
 */
public class EventBroker {

	/** The logger */
	private static Logger log = LoggerFactory.getLogger(EventBroker.class);
	
	/** The event broker instance */
	public static final EventBroker INSTANCE = new EventBroker();

	/** The pool for worker threads */
	protected ExecutorService workerPool = null;

	/** The timer */
	protected TimerSignaling timer = null;
	
	/**
	 * Constructor.
	 */
	private EventBroker() {
		// Start worker pool
		workerPool = Executors.newFixedThreadPool(30);
		// Start the timer
		timer = new TimerSignaling(workerPool);
		workerPool.execute(timer);
		
    	// Stop the broker oin shutdown again
    	final Thread mainThread = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
		        try {
					mainThread.join();
			    	shutdown();
				} catch (InterruptedException e) {
					log.error("Error while shutting down");
				}
		    }
		});    	

	}

	/**
	 * Enqueues the event for publishing.
	 * @param data - the data for publishing
	 */
	public void publish(EventData data) {
		workerPool.execute(new EventHandler(workerPool, data));
	}

	/**
	 * Shutdown the broker service.
	 */
	protected void shutdown() {
		// Stop the time
		timer.stopRunning();
		// Disable new tasks from being submitted
		workerPool.shutdown(); 
		try {
			long start = System.currentTimeMillis();
			// Wait a while for existing tasks to terminate
			if (!workerPool.awaitTermination(50000, TimeUnit.MILLISECONDS)) {
				// Cancel currently executing tasks
				workerPool.shutdownNow();
				// Wait a while for tasks to respond to being cancelled
				long remaining = 60000L - (System.currentTimeMillis() - start);
				if (!workerPool.awaitTermination(remaining, TimeUnit.MILLISECONDS))
					log.error("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			workerPool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}		
	}
}
