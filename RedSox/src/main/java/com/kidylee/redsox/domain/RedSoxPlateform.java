package com.kidylee.redsox.domain;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class RedSoxPlateform {

	private ConnectionManager connectionManager;
	private HealthChecker checker;

	@Autowired
	private EventBus connectionManagerEventBus;

	public void start() {

		connectionManagerEventBus.register(connectionManager);
		connectionManager.startAll();
		checker.start();

		// TODO externalize configuration
		// TODO start websocket
		// TODO handle websocket exception and start rest
		// TODO regular check webcoket available
		// TODO reconnect websocket
	}

	@Subscribe
	public void restart(HealthChecker checker) {
		if (!checker.running) {
			checker.start();
		}
	}

	public void stop() {
		checker.running = false;
		connectionManager.stopAll();
	}

}
