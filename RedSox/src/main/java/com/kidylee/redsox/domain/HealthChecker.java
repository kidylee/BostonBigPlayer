package com.kidylee.redsox.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.EventBus;
import com.kidylee.coach.domain.Market;

public class HealthChecker implements Runnable {

	@Autowired
	ConnectionManager connectionManager;

	@Autowired
	private EventBus connectionManagerEventBus;

	public boolean running = true;

	final static HealthChecker checker = getInstance();
	public final long THRESHOLD = 7 * 1000L; // 5 seconds
	public final long HEART_BEAT_PRIOD = 3 * 1000; // 3 seconds

	private final Map<Market, Long> lastFineReports = new ConcurrentHashMap<>();

	/**
	 * Each thread should call this method periodically.
	 */
	public void iAmFine(Market market) {
		lastFineReports.put(market, Long.valueOf(System.currentTimeMillis()));
	}

	private HealthChecker() {

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
			for (MarketConnection conn : connectionManager.getActiveConnections()) {

				if (now - lastFineReports.get(conn.getMarket()) > THRESHOLD)
					connectionManagerEventBus.post(conn);
				conn.heartBeat();
			}

		}

	}

	public static HealthChecker getInstance() {
		if (checker == null)
			return new HealthChecker();

		return checker;

	}

	/**
	 * Used by whatchdog to know whether everything is OK.
	 */
	public boolean isEverythingOK() {
		Long[] timestamps = lastFineReports.values().toArray(new Long[lastFineReports.size()]);

		long now = System.currentTimeMillis();
		for (Long t : timestamps)
			if (now - t.longValue() > THRESHOLD)
				return false;

		return true;
	}

	public void check(MarketConnection conn) {
		// TODO Auto-generated method stub

	}

	public void protect(ConnectionManager connectionManager) {
		// TODO Auto-generated method stub

	}

	public void start() {
		new Thread(this).start();

	}

}