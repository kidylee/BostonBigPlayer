package com.kidylee.redsox.domain;

public abstract class HeartBeat {

	private long lastTime = System.currentTimeMillis();

	private long proid = 3 * 1000L; // 3 sec

	public boolean onTime() {
		return lastTime + proid < System.currentTimeMillis();

	}
	
	public abstract void sendPing();

}
