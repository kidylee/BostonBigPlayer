package com.kidylee.redsox.okcoin.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.kidylee.redsox.domain.OrderBookPage;

public class OKCoinFutureDepth {
	private OrderBookPage asks;
	private OrderBookPage bids;;
	private Long timestamp;
	private Integer unitAmount;

	public OrderBookPage getAsks() {
		return asks;
	}

	public void setAsks(OrderBookPage asks) {
		this.asks = asks;
	}

	public OrderBookPage getBids() {
		return bids;
	}

	public void setBids(OrderBookPage bids) {
		this.bids = bids;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getUnitAmount() {
		return unitAmount;
	}

	public void setUnitAmount(Integer unitAmount) {
		this.unitAmount = unitAmount;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

}
