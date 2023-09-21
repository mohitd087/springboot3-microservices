package com.currency.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.currency.lib.CurrencyParameters;

@Component
@FeignClient(name="currencyExchang" ,url = "localhost:8081")
public interface CurrencyServiceInterface {

	@GetMapping("currencyExchange/{from}/currency/{to}")
	public ResponseEntity<CurrencyParameters> exchangeServic(@PathVariable String from, @PathVariable String to);
}