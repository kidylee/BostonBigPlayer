package com.kidylee.redsox.domain;

public interface MarketConnection {

	MarketConnection websocket();

	MarketConnection subscribe();

	MarketConnection register();

}
