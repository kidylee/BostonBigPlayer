package com.kidylee.redsox.okcoin.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kidylee.redsox.domain.Tick;



public class OKCoinFutureTick {


	@SerializedName("buy")
	@Expose
	public Double buy;
	@SerializedName("contractId")
	@Expose
	public String contractId;
	@SerializedName("high")
	@Expose
	public Double high;
	@SerializedName("last")
	@Expose
	public BigDecimal price;
	@SerializedName("low")
	@Expose
	public Double low;
	@SerializedName("sell")
	@Expose
	public Double sell;
	@SerializedName("unitAmount")
	@Expose
	public Integer unitAmount;
	@SerializedName("vol")
	@Expose
	public BigDecimal volume;

	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	



}