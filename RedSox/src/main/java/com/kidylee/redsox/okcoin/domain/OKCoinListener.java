package com.kidylee.redsox.okcoin.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kidylee.redsox.domain.MarketConnection;
import com.kidylee.redsox.domain.NVListenerAdapter;
import com.kidylee.redsox.domain.OrderBook;
import com.kidylee.redsox.domain.Tick;
import com.kidylee.redsox.okcoin.repository.FutureTickerRepository;
import com.kidylee.redsox.okcoin.repository.OrderBookRepository;
import com.kidylee.redsox.okcoin.repository.TickRepository;
import com.neovisionaries.ws.client.WebSocket;

@Component
public class OKCoinListener extends NVListenerAdapter {

	public OKCoinListener(MarketConnection conn) {
		super(conn);
	}

	@Autowired
	FutureTickerRepository repostory;

	@Autowired
	TickRepository tickRepository;

	@Autowired
	OrderBookRepository orderBookRepository;

	private final Logger log = LoggerFactory.getLogger(OKCoinListener.class);

	@Override
	public void onTextMessage(WebSocket websocket, String text) throws Exception {
		log.debug(text);

		if ("{\"event\":\"pong\"}".equals(text)) {
			onHeartBeat();
			return;
		}

		try {
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(text).getAsJsonArray();
			for (JsonElement el : array) {

				Channel chanel = Channel.valueOf(el.getAsJsonObject().get("channel").toString());
				Object response = chanel.parseResponse(el);
				if (response instanceof Tick) {
					tickRepository.save((Tick) response);
				}

				if (response instanceof OrderBook) {
					orderBookRepository.save((OrderBook) response);
				}
				//TODO notify
			}
		} catch (Exception e) {
			log.error("Websocket listener onTextMessage error: ", e);
		}

	}

}
