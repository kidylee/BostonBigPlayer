package com.kidylee.redsox.okcoin.domain;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.kidylee.redsox.domain.OrderBookItem;
import com.kidylee.redsox.domain.OrderBookPage;

public class OKCoinFutureDepthTypeAdapter extends TypeAdapter<OKCoinFutureDepth> {

	@Override
	public void write(JsonWriter out, OKCoinFutureDepth value) throws IOException {
		throw new NotImplementedException("Write OrderBookItem");

	}

	@Override
	public OKCoinFutureDepth read(JsonReader in) throws IOException {
		OKCoinFutureDepth fd = new OKCoinFutureDepth();
		OrderBookPage asks = new OrderBookPage();
		OrderBookPage bids = new OrderBookPage();
		List<OrderBookItem> askItems = new ArrayList<>();
		List<OrderBookItem> bidItems = new ArrayList<>();
		fd.setAsks(asks);
		fd.setBids(bids);
		asks.setOrderBookItem(askItems);
		bids.setOrderBookItem(bidItems);

		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
			case "asks":
				parseOrderBookItem(in, askItems);
				break;
			case "bids":
				parseOrderBookItem(in, bidItems);
				break;
			case "timestamp":
				fd.setTimestamp(in.nextLong());
				break;
			case "unit_amount":
				fd.setUnitAmount(in.nextInt());
				break;
			}

		}
		in.endObject();
		return fd;
	}

	private void parseOrderBookItem(JsonReader in, List<OrderBookItem> askItems) throws IOException {
		OrderBookItem item = new OrderBookItem();
		in.beginArray();
		while (in.hasNext()) {
			in.beginArray();
			item.setPrice(BigDecimal.valueOf(in.nextDouble()));
			item.setVolume(BigDecimal.valueOf(in.nextDouble()));
			in.endArray();
			askItems.add(item);

		}
		in.endArray();
	}

}