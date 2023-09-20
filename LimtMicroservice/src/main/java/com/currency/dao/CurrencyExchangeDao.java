package com.currency.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.currency.lib.CurrencyParameters;

@Repository
public interface CurrencyExchangeDao extends JpaRepository<CurrencyParameters, Long> {

	public CurrencyParameters findByFromAndTo(String from,String to);
}
