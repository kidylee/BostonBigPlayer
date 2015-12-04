package com.kidylee.redsox.okcoin.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OKCoinFutureIndex {
	private BigDecimal futureIndex;

	private Long timestamp;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
	
	public Date getTimestamp(){
		return new Date(timestamp);
	}
}
