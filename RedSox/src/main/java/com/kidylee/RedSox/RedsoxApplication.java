package com.kidylee.RedSox;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.kidylee.RedSox.OKCoin.domain.OKCoin;

@SpringBootApplication
public class RedsoxApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RedsoxApplication.class, args);
        OKCoin coin = context.getBean(OKCoin.class);
        coin.init();
        
        CountDownLatch latch = new CountDownLatch(1);
        try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
