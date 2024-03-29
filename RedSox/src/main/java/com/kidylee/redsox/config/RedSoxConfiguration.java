package com.kidylee.redsox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.eventbus.EventBus;

@Configuration
public class RedSoxConfiguration {

	final static EventBus marketConnectionManagerEventBus = new EventBus();

	@Bean
	public EventBus getMarketConnectionManagerEventBus() {
		return marketConnectionManagerEventBus;
	}

}
