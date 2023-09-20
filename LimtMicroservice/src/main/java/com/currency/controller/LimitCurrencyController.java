package com.currency.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.currency.dao.CurrencyExchangeDao;
import com.currency.lib.CurrencyParameters;

@RestController
public class LimitCurrencyController {

	
	@Autowired
	CurrencyExchangeDao exchangeDao;
	@GetMapping("limitService/{from}/currency/{to}/{quantity}")
	public ResponseEntity<CurrencyParameters> limitService(@PathVariable String from, @PathVariable String to,
			@PathVariable Integer quantity) {
			HashMap<String,String> idParameter=new HashMap<>();
			idParameter.put("from",from);
			idParameter.put("to", to);
			CurrencyParameters result=new RestTemplate().getForEntity("http://localhost:8081/currencyExchange/{from}/currency/{to}",CurrencyParameters.class, idParameter).getBody();
		
			return new ResponseEntity<CurrencyParameters>(new CurrencyParameters(from,to,new BigDecimal(quantity).multiply(new BigDecimal(result.getConversion_amount()))),HttpStatus.OK);

	}

}
