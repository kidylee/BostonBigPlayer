package com.kidylee.redsox.okcoin.domain;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class FutureTickerResponse implements OKCoinResponse {

	@SerializedName("channel")
	@Expose
	private Channel channel;
	@SerializedName("data")
	@Expose
	private FutureTicker futureTicker;

	public FutureTicker getFutureTicker() {
		return futureTicker;
	}

	public void setFutureTicker(FutureTicker futureTicker) {
		this.futureTicker = futureTicker;
	}

	private static Gson gson = new Gson();

	static public FutureTickerResponse toFutureTickerResponse(String response) {

		return gson.fromJson(response, FutureTickerResponse.class);

	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public Channel getChannel() {
		return channel;
	}

	@Override
	public OKCoinData getData() {
		return futureTicker;
	}
}