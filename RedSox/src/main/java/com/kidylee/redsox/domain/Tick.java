package com.kidylee.redsox.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class Tick {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public BigDecimal price;

	public BigDecimal volume;

	@ManyToOne
	public Long assetId;

	public Date createdAt;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

}
