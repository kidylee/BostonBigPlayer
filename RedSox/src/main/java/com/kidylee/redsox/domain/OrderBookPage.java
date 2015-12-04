package com.kidylee.redsox.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderBookPage{
	
	private List<OrderBookItem> orderBookItem;

	public List<OrderBookItem> getOrderBookItem() {
		return orderBookItem;
	}

	public void setOrderBookItem(List<OrderBookItem> orderBookItem) {
		this.orderBookItem = orderBookItem;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}