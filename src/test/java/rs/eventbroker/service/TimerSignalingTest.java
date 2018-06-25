/**
 * 
 */
package rs.eventbroker.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rs.baselib.util.RsDate;
import rs.eventbroker.queue.EventHandler;
import rs.eventbroker.queue.TimerSignaling;

/**
 * Testing the timer signaling
 * @author ralph
 *
 */
public class TimerSignalingTest implements Executor {

	private static final Logger log = LoggerFactory.getLogger(TimerSignalingTest.class);
	
	private List<EventData> events = new ArrayList<>();
	
	@Test
	public void testSignaling() {
		TimerSignaling timer = new TimerSignaling(this);
		new Thread(timer).start();
		try {
			RsDate now = new RsDate();
			int seconds = 63-now.get(Calendar.SECOND);
			log.info("Waiting for next minute. The time now is: "+now.toString());
			Thread.sleep(seconds *1000L);
			timer.stopRunning();
			log.info("Waiting for timer to stop");
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			
		}
		// Test it (Fixed test problem when 2 events occurred)
		assertTrue("Did not get timer event", events.size() > 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Runnable command) {
		EventData event = ((EventHandler)command).getEvent();
		log.info("TIMER: "+event.toString());
		events.add(event);
	}

	
}
