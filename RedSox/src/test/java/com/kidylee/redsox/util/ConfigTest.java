package com.kidylee.redsox.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.junit.Test;

import com.google.gson.Gson;
import com.kidylee.redsox.okcoin.config.OKCoinConfig;
import com.kidylee.redsox.okcoin.domain.OKCoinChannel;
import com.kidylee.redsox.okcoin.domain.OKCoinRequest;

public class ConfigTest {

	@Test
	public void test() {
		OKCoinConfig cfg = ConfigFactory.create(OKCoinConfig.class);
		assertEquals(cfg.websocketURL(), "wss://real.okcoin.com:10440/websocket/okcoinapi");
		assertEquals(cfg.heartbeat(), "{'event':'ping'}");
		assertTrue(cfg.channels() != null && cfg.channels().size() > 1);
	}

	@Test
	public void testChangeToRequest() {
		OKCoinConfig config = ConfigFactory.create(OKCoinConfig.class);
		List<OKCoinRequest> requests = new ArrayList<>();
		for (OKCoinChannel channel : config.channels()) {
			requests.add(new OKCoinRequest(channel));
		}
		Gson gson = new Gson();
		System.out.println(gson.toJson(requests));
		assertNotNull(gson.toJson(requests));
	}

}
