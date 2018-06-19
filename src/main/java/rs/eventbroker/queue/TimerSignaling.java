/**
 * 
 */
package rs.eventbroker.queue;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;

import rs.baselib.util.RsDate;
import rs.eventbroker.service.EventData;

/**
 * A long-time running worker that signal every minute for timing events.
 * <p>Purpose is to implement cron-alike events</p>
 * @author ralph
 *
 */
public class TimerSignaling implements Runnable {

	private volatile boolean stopRunning = false;

	/** The pool for worker threads */
	protected ExecutorService workerPool = null;

	/**
	 * Constructor.
	 */
	public TimerSignaling(ExecutorService workerPool) {
		this.workerPool = workerPool;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		while (!stopRunning) {
			RsDate now = new RsDate();
			// Loop until the next full minute
			while (!stopRunning && (now.get(Calendar.SECOND) > 0)) {
				// Sleep until next second
				int ms = 1100 - now.get(Calendar.MILLISECOND);
				try {
					Thread.sleep(ms);
				} catch (Throwable t) {
					// Ignore silently
				}
				now = new RsDate();
			}

			// We shall now be at a full minute
			if (!stopRunning) {
				EventData event = new EventData();
				event.setPacketId("TIMER-"+now.getTimeInMillis());
				StringBuilder topic = new StringBuilder();
				topic.append("timer/min/");
				topic.append(now.get(Calendar.MINUTE));
				topic.append("/hour/");
				topic.append(now.get(Calendar.HOUR_OF_DAY));
				topic.append("/day/");
				topic.append(now.get(Calendar.DAY_OF_MONTH));
				topic.append("/weekday/");
				topic.append(now.get(Calendar.DAY_OF_WEEK));
				topic.append("/month/");
				topic.append(now.get(Calendar.MONTH)+1);
				event.setTopicName(topic.toString());
				event.setPayload("");
				event.setQos(0);
				event.setRetainFlag(false);
				event.setDupFlag(false);
				workerPool.execute(new EventHandler(workerPool, event));
			}
		}
	}

	/**
	 * Stop this timer signaling.
	 */
	public void stopRunning() {
		this.stopRunning = true;
	}
}
