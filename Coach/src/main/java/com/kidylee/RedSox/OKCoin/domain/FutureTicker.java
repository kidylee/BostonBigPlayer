package com.kidylee.RedSox.OKCoin.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
@Generated("org.jsonschema2pojo")
public class FutureTicker {

	@Id
//	@SequenceGenerator(name = "future_ticker_seq", sequenceName = "future_ticker_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "webuser_idwebuser_seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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
	public Double last;
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
	public Double volume;

	public Date createAt = new Date();
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	public void persist() {
		// TODO Auto-generated method stub

	}

}