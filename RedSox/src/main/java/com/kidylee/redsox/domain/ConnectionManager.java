package com.kidylee.redsox.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;
import com.kidylee.redsox.util.PropertiesUtils;

public class ConnectionManager {

	private static final Logger log = LoggerFactory.getLogger(ConnectionManager.class);
	List<Market> markets = new ArrayList<>();

	public ConnectionManager() {
		markets = PropertiesUtils.getMarkets();

	}

	public void startAll() {
		log.info("Starting market data....");
		for (Market market : markets) {
			boolean useRest = false;
			MarketConnection conn = market.getMarketConnection();
			try {
				log.info("Starting {}.", market);

				conn.createWebSocket().registerListener().startWebSocket();

				log.info("{} started.", market);

			} catch (Exception ex) {
				log.warn("Websocket faile to connect to market: {}. See the detail {}", conn.getMarket(), ex);
				log.warn("{} failed back to use rest API", conn.getMarket());
				useRest = true;

			}

			if (useRest) {
				conn.startRest();
				retryWebSocket(conn);
			}
		}

	}

	private void retryWebSocket(MarketConnection conn) {
		// TODO retry websocket
	}

	@Subscribe
	public void onConnectionFail(MarketConnection conn) {
		boolean useRest = false;
		conn.disconnectWebsocket();
		try {
			conn.restartWebSocket();
		} catch (Exception ex) {
			log.warn("Websocket faile to connect to market: {}. See the detail {}", conn.getMarket(), ex);
			useRest = true;
		}

		if (useRest) {
			conn.startRest();
			retryWebSocket(conn);
		}
	}

	public void stopAll() {
		log.info("Stopping market data....");
		for (Market market : markets) {

			MarketConnection conn = market.getMarketConnection();
			try {
				log.info("Stopping {}.", market);

				conn.disconnectWebsocket();
				conn.stopRest();
				log.info("{} stopped.", market);

			} catch (Exception ex) {
				log.warn("Websocket faile to disconnect, market: {}. See the detail {}", conn.getMarket(), ex);

			}

		}

	}

}
