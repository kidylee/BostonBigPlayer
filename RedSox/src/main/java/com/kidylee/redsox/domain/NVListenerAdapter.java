package com.kidylee.redsox.domain;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.EventBus;
import com.kidylee.redsox.okcoin.repository.FutureTickerRepository;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketState;

public abstract class NVListenerAdapter implements WebSocketListener{

	long lastUpdate = System.currentTimeMillis();
	
	private MarketConnection conn;
	
	@Autowired
	private EventBus marketConnectionManagerEventBus;
	
	@Autowired
	HealthChecker checker;

	public NVListenerAdapter(MarketConnection conn) {
		this.conn = conn;
	}

	public void onHeartBeat() {
		checker.iAmFine(conn.getMarket());
		
	}

	private void onException() {
		marketConnectionManagerEventBus.post(conn);
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
		
		
	}

	@Override
	public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame,
			boolean closedByServer) throws Exception {
		log.info("Websocket onDisconnected, colsed  by servier: {} ", closedByServer);
		
	}

	@Override
	public void onFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		log.debug("Websocket on frame: {} ", frame);
		
	}

	@Override
	public void onContinuationFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		log.debug("Websocket on Continuation Frame: {} ", frame);
		
	}

	@Override
	public void onTextFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		log.debug("Websocket on Continuation Frame: {} ", frame);
		
	}

	@Override
	public void onBinaryFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		log.debug("Websocket on binary Frame: {} ", frame);

		
	}

	@Override
	public void onCloseFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		log.debug("Websocket on Close Frame: {} ", frame);

		
	}

	@Override
	public void onPingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		log.debug("Websocket on Ping Frame: {} ", frame);

		
	}

	@Override
	public void onPongFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
		log.debug("Websocket on Pong Frame: {} ", frame);

		
	}

	@Override
	abstract public void onTextMessage(WebSocket websocket, String text) throws Exception;

	@Override
	public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
		log.debug("Websocket on binary message: {} ", binary);
		
	}

	@Override
	public void onFrameSent(WebSocket websocket, WebSocketFrame frame) throws Exception {
		log.debug("Websocket on frame sent: {} ", frame);
		
	}

	@Override
	public void onFrameUnsent(WebSocket websocket, WebSocketFrame frame) throws Exception {
		log.debug("Websocket on frame unsent: {} ", frame);
		
	}

	@Override
	public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
		log.error("Websocket get error: ", cause);
		onException();
		
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
