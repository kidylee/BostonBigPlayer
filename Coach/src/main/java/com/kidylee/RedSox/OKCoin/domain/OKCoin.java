package com.kidylee.RedSox.OKCoin.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.kidylee.RedSox.OKCoin.domain.Channel.*;
import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

@Component
public class OKCoin {
	
	
	
	private static final Logger log = LoggerFactory.getLogger(OKCoin.class);
	
	private final static String INTERNATIONAL_URI = "wss://real.okcoin.com:10440/websocket/okcoinapi";
	
	public Exception expcetion;
	WebSocket websocket;
	
	@Autowired
	OKCoinListener listener;
	
	

	public void init() {
		expcetion = null;
		try {
			websocket = new WebSocketFactory().createSocket(INTERNATIONAL_URI);
			
			websocket.addListener(listener);
			websocket.connect();
		} catch (IOException | WebSocketException e) {
			websocket.disconnect();
			log.error("Initial OKCoin connection error {}", e);
			expcetion = e;
		}
		
		this.subscrib(new Channel[]{ok_btcusd_future_ticker_this_week,ok_btcusd_future_ticker_next_week});
		
		
	}
	
	public void subscrib(Channel... channels){
		List<OKCoinRequest> requests = new ArrayList<>();
		for(Channel channel : channels){
			requests.add(new OKCoinRequest(channel));
		}
		Gson gson = new Gson();
		
		log.info("Subscribe channels: {}",gson.toJson(requests));
		websocket.sendText(gson.toJson(requests));
	}
	
	@Scheduled(fixedRate=5*1000, initialDelay=10*1000)
	public void sendPing(){
		websocket.sendText("{'event':'ping'}");

	}
	
}
