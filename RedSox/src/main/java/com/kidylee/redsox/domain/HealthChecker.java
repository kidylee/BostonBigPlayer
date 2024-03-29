package com.kidylee.redsox.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

@Component
public class HealthChecker implements Runnable {

	@Autowired
	ConnectionManager connectionManager;

	@Autowired
	private EventBus connectionManagerEventBus;

	public boolean running = true;

	public final long THRESHOLD = 7 * 1000L; // 5 seconds
	public final long HEART_BEAT_PRIOD = 3 * 1000; // 3 seconds

	private final Map<Market, Long> lastFineReports = new ConcurrentHashMap<>();

	/**
	 * Each thread should call this method periodically.
	 */
	public void iAmFine(Market market) {
		lastFineReports.put(market, Long.valueOf(System.currentTimeMillis()));
	}

	
	@Override
	public void run() {
		while (running) {

			try {

				Thread.sleep(HEART_BEAT_PRIOD);

			} catch (InterruptedException e) {

				running = false;
				connectionManagerEventBus.post(this);
				break;
			}

			long now = System.currentTimeMillis();
			for (Market market : lastFineReports.keySet()) {

				if (now - lastFineReports.get(market) > THRESHOLD){
					connectionManagerEventBus.post(market.getMarketConnection());
					continue;
				}
				market.getMarketConnection();
			}

		}

	}

	



	public void start() {
		new Thread(this).start();

	}

}