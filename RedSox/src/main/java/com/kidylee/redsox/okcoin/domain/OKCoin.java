package com.kidylee.redsox.okcoin.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kidylee.redsox.domain.Market;
import com.kidylee.redsox.domain.MarketConnection;

@Component
public class OKCoin implements Market {

	@Autowired
	OKCoinMarketConnection okConn;

	@Override
	public MarketConnection getMarketConnection() {

		return okConn;

	}

	

}
