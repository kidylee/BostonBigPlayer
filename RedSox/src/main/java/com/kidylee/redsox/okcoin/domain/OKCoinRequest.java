package com.kidylee.redsox.okcoin.domain;

public class OKCoinRequest {

	public final String event = "addChannel";
	public OKCoinChannel channel;
	public OKCoinRequest(OKCoinChannel channel) {
		super();
		this.channel = channel;
	}

}
