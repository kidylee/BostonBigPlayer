package com.kidylee.RedSox.OKCoin.domain;

public class OKCoinRequest {

	public final String event = "addChannel";
	public Channel channel;
	public OKCoinRequest(Channel channel) {
		super();
		this.channel = channel;
	}

}
