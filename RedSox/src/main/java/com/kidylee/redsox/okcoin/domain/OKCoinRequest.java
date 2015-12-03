package com.kidylee.redsox.okcoin.domain;

public class OKCoinRequest {

	public final String event = "addChannel";
	public Channel channel;
	public OKCoinRequest(Channel channel) {
		super();
		this.channel = channel;
	}

}
