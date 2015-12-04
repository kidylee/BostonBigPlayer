package com.kidylee.redsox.okcoin.domain;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class OKCoinListenerTest {

	@Test
	public void test() {
		String response = "[{    'channel':'ok_btcusd_future_depth_this_week',	'data':{	    'asks':[		    [398.76,4],			[398.55,20],			[398.35,14],			[397.98,98],			[397.63,4]		],		'bids':[		    [397.25,4],			[397.03,17],			[396.92,8],			[396.79,21],			[396.69,35]		],		'timestamp':'1418636773053',		'unit_amount':100	}}]";
	
		JsonParser parser = new JsonParser();
		
		
		JsonArray array = parser.parse(response).getAsJsonArray();
		JsonElement el = array.get(0);
		System.out.println(el.getAsJsonObject().get("channel"));
	
	}

}
