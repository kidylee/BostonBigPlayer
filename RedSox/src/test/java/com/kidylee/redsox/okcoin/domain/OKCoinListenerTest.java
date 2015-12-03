package com.kidylee.redsox.okcoin.domain;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class OKCoinListenerTest {

	@Test
	public void test() {
		String response = "[{  'channel':'ok_future_ltcusd_kline_quarter_1min',  'data':{       [[1414989360000,3.658,3.658,3.658,3.658,313],	   [1414989420000,3.658,3.658,3.658,3.658,0]]   }}]";
	
		JsonParser parser = new JsonParser();
		
		JsonElement el = parser.parse(response);
		
//		JsonArray array = parser.parse(response).getAsJsonArray();
//		JsonElement el = array.get(0);
		System.out.println(el);
	
	}

}
