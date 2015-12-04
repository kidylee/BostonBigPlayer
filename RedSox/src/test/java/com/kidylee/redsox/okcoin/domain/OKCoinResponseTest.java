package com.kidylee.redsox.okcoin.domain;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.kidylee.redsox.domain.OrderBookPage;



public class OKCoinResponseTest {

	Gson gson = new Gson();
	@Test
	public void testGetTick() {
		String json = "{  \"channel\":\"ok_btcusd_future_ticker_this_week\",  \"data\":{        \"buy\":396.93,		\"contractId\":20141003011,		\"high\":405.35,		\"last\":397.2,		\"low\":392.73,		\"sell\":397.6,		\"unitAmount\":100,		\"vol\":119769   }}";
		OKCoinResponse response = gson.fromJson(json, OKCoinResponse.class);
		Assert.assertNotNull(response);
		System.out.println(response);
		Assert.assertNotNull(response.getTick());
		System.out.println(response.getTick());
	}

	@Test
	public void testGetFutureIndex() {
		String json = " {        \"channel\": \"ok_btcusd_future_index\",        \"data\": {            \"futureIndex\": 243.3771,            \"timestamp\": \"1422599463173\"        }    }";
		OKCoinResponse response = gson.fromJson(json, OKCoinResponse.class);
		Assert.assertNotNull(response);
		System.out.println(response);
		Assert.assertNotNull(response.getFutureIndex());
		System.out.println(response.getFutureIndex());
		System.out.println(response.getFutureIndex().getTimestamp());

	}
	
	@Test
	public void testGetOrderBook() {
		String json = "{    \"channel\":\"ok_btcusd_future_depth_this_week\",	\"data\":{	    \"asks\":[		    [398.76,4],			[398.55,20],			[398.35,14],			[397.98,98],			[397.63,4]		],		\"bids\":[		    [397.25,4],			[397.03,17],			[396.92,8],			[396.79,21],			[396.69,35]		],		\"timestamp\":\"1418636773053\",		\"unit_amount\":100	}}";
		OKCoinResponse response = gson.fromJson(json, OKCoinResponse.class);
		Assert.assertNotNull(response);
		System.out.println(response);
		Assert.assertNotNull(response.getFutureDepth());
		System.out.println(response.getFutureDepth());
		

	}
	
	@Test 
	public void testGson() throws IOException{
		final GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.registerTypeAdapter(OKCoinFutureDepth.class, new OKCoinFutureDepthTypeAdapter().nullSafe());
	    final Gson gson = gsonBuilder.create();
	    
		String json = "{	    \"asks\":[		    [398.76,4],			[398.55,20],			[398.35,14],			[397.98,98],			[397.63,4]		],		\"bids\":[		    [397.25,4],			[397.03,17],			[396.92,8],			[396.79,21],			[396.69,35]		],		\"timestamp\":\"1418636773053\",		\"unit_amount\":100	}";
		
		OKCoinFutureDepth fd = gson.fromJson(json, OKCoinFutureDepth.class);
		Assert.assertNotNull(fd);
		System.out.println(fd);
		
		
	}

}
