package com.kidylee.redsox.domain;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.EventBus;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kidylee.redsox.okcoin.domain.FutureTicker;
import com.kidylee.redsox.okcoin.domain.FutureTickerResponse;
import com.kidylee.redsox.okcoin.domain.OKCoinListener;
import com.kidylee.redsox.okcoin.repository.FutureTickerRepository;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketState;

public abstract class BaseListener implements WebSocketListener{

	long lastUpdate = System.currentTimeMillis();
	
	private MarketConnection conn;
	
	@Autowired
	private EventBus connectionManagerEventBus;
	
	@Autowired
	HealthChecker checker;

	public BaseListener(MarketConnection conn) {
		this.conn = conn;
	}

	public void onHeartBeat() {
		checker.iAmFine(conn.getMarket());
		
	}

	public void onException() {
		connectionManagerEventBus.post(conn);
	}
	

	@Autowired
	FutureTickerRepository repostory;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onStateChanged(WebSocket websocket, WebSocketState newState) throws Exception {
		log.debug("Websocket status changed: {} ", newState);

		
	}

	@Override
	public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
		log.info("Websocket on Connected: {} ", headers);
		
	}

	@Override
	public void onConnectError(WebSocket websocket, WebSocketException cause) throws Exception {
		log.error("Websocket onConnectError ", cause);
		onException();
		
	}

	@Override
	public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame,
			boolean closedByServer) throws Exception {
		log.info("Websocket onDisconnected, colsed  by servier: {} ", closedByServer);
		
	}

	@Override
	public void onFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		
		
	}

	@Override
	public void onContinuationFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		
		
	}

	@Override
	public void onTextFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		
		
	}

	@Override
	public void onBinaryFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		
		
	}

	@Override
	public void onCloseFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		
		
	}

	@Override
	public void onPingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		
		
	}

	@Override
	public void onPongFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		
		
	}

	@Override
	public void onTextMessage(WebSocket websocket, String text) throws Exception {
		try{
			log.debug(text);
			
			if("{\"event\":\"pong\"}".equals(text))
				return;
			
			JsonParser parser = new JsonParser();
		    JsonArray array = parser.parse(text).getAsJsonArray();
		    for(JsonElement el : array){
		    	
		    	FutureTicker t = FutureTickerResponse.toFutureTickerResponse(el).getFutureTicker();
		    	repostory.save(t);
		    }
		}catch(Exception e){
			log.error("Websocket listener onTextMessage error: ",e);
		}
		
	    	
	    
		
	}

	@Override
	public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFrameSent(WebSocket websocket, WebSocketFrame frame) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFrameUnsent(WebSocket websocket, WebSocketFrame frame) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
		log.error("Websocket get error: ", cause);
		
	}

	@Override
	public void onFrameError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {
		log.error("Websocket get error: ", cause);
		
	}

	@Override
	public void onMessageError(WebSocket websocket, WebSocketException cause, List<WebSocketFrame> frames)
			throws Exception {
		log.error("Websocket get error: ", cause);
		
	}

	@Override
	public void onTextMessageError(WebSocket websocket, WebSocketException cause, byte[] data) throws Exception {
		log.error("Websocket get error: ", cause);
		
	}

	@Override
	public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {
		log.error("Websocket get error: ", cause);
		
	}

	@Override
	public void onUnexpectedError(WebSocket websocket, WebSocketException cause) throws Exception {
		log.error("Websocket get error: ", cause);
		
	}

	@Override
	public void handleCallbackError(WebSocket websocket, Throwable cause) throws Exception {
		log.error("Websocket get error: ", cause);
		
	}

}
