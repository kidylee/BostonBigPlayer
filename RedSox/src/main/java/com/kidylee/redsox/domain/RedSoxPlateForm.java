package com.kidylee.redsox.domain;

public class RedSoxPlateForm {
	
	private ConnectionManager connectionManager = new ConnectionManager();
	
	public void start(){
		MarketConnection okCoinConnection = connectionManager.getMarketConnection(OKCoin.class);
		
		okCoinConnection.websocket().subscribe().register();
		
		//TODO externalize configuration
		//TODO start websocket
		//TODO handle websocket exception and start rest
		//TODO regular check webcoket available 
		//TODO reconnect websocket
	}

}
