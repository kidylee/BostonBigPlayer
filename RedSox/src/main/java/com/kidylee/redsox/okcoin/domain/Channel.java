package com.kidylee.redsox.okcoin.domain;

import com.google.gson.JsonElement;
import com.kidylee.redsox.domain.OrderBook;
import com.kidylee.redsox.domain.Tick;

public enum Channel {
	ok_btcusd_future_ticker_this_week {
		@Override
		public Tick parseResponse(JsonElement response) {
			return null;

		}
	},
	ok_btcusd_future_ticker_next_week {
		@Override
		public Tick parseResponse(JsonElement response) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	ok_btcusd_future_ticker_quarter {
		@Override
		public Tick parseResponse(JsonElement response) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	ok_btcusd_future_index {
		@Override
		public Tick parseResponse(JsonElement response) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	ok_btcusd_future_depth_this_week {
		@Override
		public OrderBook parseResponse(JsonElement response) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	ok_btcusd_future_depth_next_week

	{
		@Override
		public OrderBook parseResponse(JsonElement response) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	ok_btcusd_future_depth_quarter {
		@Override
		public OrderBook parseResponse(JsonElement response) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public abstract Object parseResponse(JsonElement el);

	public static Channel indicateChannel(String response) {

		for (Channel channel : Channel.values()) {
			if (response != null && response.contains(channel.name()))
				return channel;
		}

		return null;
	}
}
