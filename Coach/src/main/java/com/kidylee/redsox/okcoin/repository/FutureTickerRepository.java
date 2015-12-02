package com.kidylee.redsox.okcoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidylee.redsox.okcoin.domain.FutureTicker;

public interface FutureTickerRepository extends JpaRepository<FutureTicker,Long> {

}
