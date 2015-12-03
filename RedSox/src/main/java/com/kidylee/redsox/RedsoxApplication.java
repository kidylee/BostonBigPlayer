package com.kidylee.redsox;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.kidylee.redsox.okcoin.domain.OKCoin;

@SpringBootApplication
public class RedsoxApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RedsoxApplication.class, args);
		OKCoin coin = context.getBean(OKCoin.class);

		CountDownLatch latch = new CountDownLatch(1);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
