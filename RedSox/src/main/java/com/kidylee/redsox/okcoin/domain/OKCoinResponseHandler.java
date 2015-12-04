package com.kidylee.redsox.okcoin.domain;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kidylee.redsox.okcoin.repository.OrderBookRepository;
import com.kidylee.redsox.okcoin.repository.TickRepository;

@Component
public class OKCoinResponseHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TickRepository tickRepository;

	@Autowired
	OrderBookRepository orderBookRepository;

	public boolean isPong(String text) {
		return "{\"event\":\"pong\"}".equals(text);
	}

	public void handle(String text) {
		List<OKCoinResponse> responses = null;
		Type listType = new TypeToken<ArrayList<OKCoinResponse>>() {
		}.getType();

		try {
			responses = new Gson().fromJson(text, listType);
		} catch (Exception e) {
			log.error("Pasrse OK Coin response error {}", e);
		}

		for (OKCoinResponse response : responses) {
			try {
				switch (response.getChannel()) {
				case ok_btcusd_future_depth_this_week:
				case ok_btcusd_future_depth_next_week:
				case ok_btcusd_future_depth_quarter:
					OKCoinFutureDepth futureDepth = response.getFutureDepth();
					break;
				case ok_btcusd_future_index:
					response.getFutureIndex();
					break;
				case ok_btcusd_future_ticker_next_week:
				case ok_btcusd_future_ticker_quarter:
				case ok_btcusd_future_ticker_this_week:
					response.getTick();
					break;

				}
			} catch (Exception e) {
				log.error("OK Coin parse channel message error, message {}, exception: {}", response, e);
			}

			// TODO notify
		}

	}

}
