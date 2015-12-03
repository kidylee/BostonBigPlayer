package com.kidylee.redsox.okcoin.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.kidylee.redsox.domain.Market;
import com.kidylee.redsox.domain.MarketConnection;
import com.kidylee.redsox.okcoin.config.OKCoinConfig;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

@Component
class OKCoinMarketConnection implements MarketConnection {

	@Autowired
	OKCoinListener listener;

	OKCoinConfig config = ConfigFactory.create(OKCoinConfig.class);
	WebSocket ws;

	private Market market;

	@Autowired
	public OKCoinMarketConnection(OKCoin market) {
		this.market = market;

	}

	@Override
	public MarketConnection startWebSocket() {
		ws.connectAsynchronously();
		heartBeat();
		subscribe();
		return this;
	}

	private MarketConnection subscribe() {
		List<OKCoinRequest> requests = new ArrayList<>();
		for (Channel channel : config.channels()) {
			requests.add(new OKCoinRequest(channel));
		}
		Gson gson = new Gson();
		String subscribes = gson.toJson(requests);
		ws.sendText(subscribes);
		return this;
	}

	@Override
	public MarketConnection registerListener() {
		ws.addListener(listener);
		return this;
	}

	@Override
	public void startRest() {
		// TODO implement rest call

	}

	@Override
	public void disconnectWebsocket() {
		if (ws != null && ws.isOpen()) {
			ws.disconnect();
		}

	}

	@Override
	public void heartBeat() {
		ws.sendText(config.heartbeat());

	}

	@Override
	public void restartWebSocket() throws WebSocketException, IOException {
		if (ws.isOpen()) {
			ws.disconnect();
		}

		ws = ws.recreate();
		startWebSocket();

	}

	@Override
	public Market getMarket() {
		return market;
	}

	@Override
	public MarketConnection createWebSocket() throws IOException {
		ws = new WebSocketFactory().createSocket(config.websocketURL());
		return this;
	}

	@Override
	public void stopRest() {
		// TODO Auto-generated method stub
		
	}

}