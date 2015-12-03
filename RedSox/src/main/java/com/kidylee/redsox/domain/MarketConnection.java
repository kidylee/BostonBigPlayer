package com.kidylee.redsox.domain;

import java.io.IOException;

import com.neovisionaries.ws.client.WebSocketException;

public interface MarketConnection {

	MarketConnection startWebSocket();

	MarketConnection registerListener();

	void startRest();

	public Market getMarket();

	void disconnectWebsocket();

	void heartBeat();

	void restartWebSocket() throws WebSocketException, IOException;

	MarketConnection createWebSocket() throws IOException;

	void stopRest();

}
