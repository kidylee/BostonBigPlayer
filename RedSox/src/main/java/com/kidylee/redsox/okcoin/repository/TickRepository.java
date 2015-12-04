package com.kidylee.redsox.okcoin.repository;

import org.springframework.data.repository.CrudRepository;

import com.kidylee.redsox.domain.Tick;

public interface TickRepository extends CrudRepository<Tick, Long> {

}
