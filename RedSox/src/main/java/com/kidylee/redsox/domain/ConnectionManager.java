package com.kidylee.redsox.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;
import com.kidylee.coach.domain.Market;
import com.kidylee.util.ConfigUtils;

public class ConnectionManager {

	private static final Logger log = LoggerFactory.getLogger(ConnectionManager.class);
	Map<Market, MarketConnection> marketConnectionMap = new HashMap<>();

	public ConnectionManager() {
		List<Market> markets = ConfigUtils.getMarkets();

		markets.forEach(
				m -> this.marketConnectionMap.computeIfAbsent(m, k -> MarketConnectionFactory.getConnection(k)));
	}

	public void startAll() {
		log.info("Starting market data....");
		for (Map.Entry<Market, MarketConnection> e : marketConnectionMap.entrySet()) {
			boolean useRest = false;
			MarketConnection conn = e.getValue();
			try {
				log.info("Starting {}.", conn.getMarket());

				conn.subscribe().registerListener().startWebSocket();

				log.info("{} started.", conn.getMarket());

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
			conn.startWebSocket();
		} catch (Exception ex) {
			log.warn("Websocket faile to connect to market: {}. See the detail {}", conn.getMarket(), ex);
			useRest = true;
		}

		if (useRest) {
			conn.startRest();
			retryWebSocket(conn);
		}
	}

	public List<MarketConnection> getActiveConnections() {
		// TODO Auto-generated method stub
		return null;
	}

	public void stopAll() {
		// TODO Auto-generated method stub
		
	}

}
