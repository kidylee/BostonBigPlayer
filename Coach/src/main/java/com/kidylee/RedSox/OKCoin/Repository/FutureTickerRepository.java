package com.kidylee.RedSox.OKCoin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidylee.RedSox.OKCoin.domain.FutureTicker;

public interface FutureTickerRepository extends JpaRepository<FutureTicker,Long> {

}
