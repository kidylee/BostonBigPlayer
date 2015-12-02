package com.kidylee.redsox.domain;

import com.kidylee.coach.domain.Market;

public interface MarketConnection {

	MarketConnection startWebSocket();

	MarketConnection subscribe();

	MarketConnection registerListener();

	MarketConnection connection();

	void startRest();

	Market getMarket();

	void disconnectWebsocket();

	void heartBeat();

}
