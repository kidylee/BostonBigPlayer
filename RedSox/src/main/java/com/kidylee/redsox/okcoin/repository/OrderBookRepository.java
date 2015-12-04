package com.kidylee.redsox.okcoin.repository;

import org.springframework.data.repository.CrudRepository;

import com.kidylee.redsox.domain.OrderBook;

public interface OrderBookRepository extends CrudRepository<OrderBook, Long> {

}
