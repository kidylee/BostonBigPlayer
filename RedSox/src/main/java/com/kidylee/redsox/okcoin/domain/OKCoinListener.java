package com.kidylee.redsox.okcoin.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kidylee.redsox.domain.MarketConnection;
import com.kidylee.redsox.domain.NVListenerAdapter;
import com.neovisionaries.ws.client.WebSocket;

@Component
public class OKCoinListener extends NVListenerAdapter {

	
	public OKCoinListener(MarketConnection conn) {
		super(conn);
	}

	@Autowired
	OKCoinResponseHandler handler;
	
	

	private final Logger log = LoggerFactory.getLogger(OKCoinListener.class);

	@Override
	public void onTextMessage(WebSocket websocket, String text) throws Exception {
		log.debug(text);
		
		if (handler.isPong(text)) {
			onHeartBeat();
			return;
		}
		
		handler.handle(text);
		

	}

}
