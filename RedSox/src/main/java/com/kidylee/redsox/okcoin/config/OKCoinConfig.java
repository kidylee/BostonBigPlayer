package com.kidylee.redsox.okcoin.config;

import java.util.List;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

import com.kidylee.redsox.okcoin.domain.OKCoinChannel;

@Sources("classpath:config/Application-RedSox.properties")
public interface OKCoinConfig extends Config {

	@Key("market.okcoin.websocket_url")
	String websocketURL();

	@Key("market.okcoin.heartbeat")
	String heartbeat();
	
	@Key("market.okcoin.channels")
	List<OKCoinChannel> channels();

}
