package com.kidylee.redsox.okcoin.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.kidylee.redsox.domain.Tick;

public class FutureTickerTest {

	@Test
	public void test() {
		FutureTicker ticker = new FutureTicker();

		ticker.price = BigDecimal.TEN;

		Assert.assertEquals(((Tick) ticker).price, BigDecimal.TEN);
		
	}

}
