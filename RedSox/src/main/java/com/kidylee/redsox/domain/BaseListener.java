package com.kidylee.redsox.domain;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseListener {

	long lastUpdate = System.currentTimeMillis();
	
	private MarketConnection conn;
	
	@Autowired
	HealthChecker checker;

	public BaseListener(MarketConnection conn) {
		this.conn = conn;
	}

	public void onHeartBeat() {
		checker.iAmFine(conn.getMarket());
		
	}


	
	public void onException() {

	}

}
