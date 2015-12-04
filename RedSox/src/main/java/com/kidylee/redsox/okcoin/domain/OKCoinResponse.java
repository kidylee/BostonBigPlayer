package com.kidylee.redsox.okcoin.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OKCoinResponse {

	final static transient Gson gson;
	@SerializedName("channel")
	@Expose
	private OKCoinChannel channel;
	@SerializedName("data")
	@Expose
	private JsonElement data;

	static {
		final GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.registerTypeAdapter(OKCoinFutureDepth.class, new OKCoinFutureDepthTypeAdapter().nullSafe());
	    gson = gsonBuilder.create();
	}
	public OKCoinFutureTick getTick() {
		return gson.fromJson(data, OKCoinFutureTick.class);
	}

	public OKCoinFutureIndex getFutureIndex() {
		return gson.fromJson(data, OKCoinFutureIndex.class);
	}
	
	public OKCoinChannel getChannel() {
		return channel;
	}

	public void setChannel(OKCoinChannel channel) {
		this.channel = channel;
	}

	public JsonElement getData() {
		return data;
	}

	public void setData(JsonElement data) {
		this.data = data;
	}

	public OKCoinFutureDepth getFutureDepth(){
		return gson.fromJson(data, OKCoinFutureDepth.class);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}
