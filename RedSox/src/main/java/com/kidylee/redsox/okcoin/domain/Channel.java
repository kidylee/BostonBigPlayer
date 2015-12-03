package com.kidylee.redsox.okcoin.domain;

public enum Channel {
	ok_btcusd_future_ticker_this_week {
		@Override
		public OKCoinResponse parseResponse(String response) {
			return FutureTickerResponse.toFutureTickerResponse(response);

		}
	},
	ok_btcusd_future_ticker_next_week {
		@Override
		public OKCoinResponse parseResponse(String response) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	ok_btcusd_future_ticker_quarter {
		@Override
		public OKCoinResponse parseResponse(String response) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	ok_btcusd_future_index {
		@Override
		public OKCoinResponse parseResponse(String response) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	ok_btcusd_future_depth_this_week {
		@Override
		public OKCoinResponse parseResponse(String response) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	ok_btcusd_future_depth_next_week

	{
		@Override
		public OKCoinResponse parseResponse(String response) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public abstract OKCoinResponse parseResponse(String response);

	public static Channel indicateChannel(String response) {

		for (Channel channel : Channel.values()) {
			if (response != null && response.contains(channel.name()))
				return channel;
		}

		return null;
	}
}
