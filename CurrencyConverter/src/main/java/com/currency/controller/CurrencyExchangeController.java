package com.currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currency.dao.CurrencyExchangeDao;
import com.currency.lib.CurrencyParameters;

@RestController
public class CurrencyExchangeController {

	@Autowired
	CurrencyExchangeDao exchangeDao;
	@Autowired
	Environment env;
	
	@GetMapping("currencyExchange/{from}/currency/{to}")
	public ResponseEntity<CurrencyParameters> exchangeService(@PathVariable String from, @PathVariable String to) {
			CurrencyParameters result=exchangeDao.findByFromAndTo(from, to);
			result.setPort(env.getProperty("local.server.port"));
		
			return new ResponseEntity<CurrencyParameters>(result,HttpStatus.OK);

	}

}
