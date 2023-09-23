package com.currency.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currency.dao.CurrencyExchangeDao;
import com.currency.lib.CurrencyParameters;
import ch.qos.logback.classic.Logger;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class LimitCurrencyController {

	@Autowired
	CurrencyServiceInterface currencyInterfaceProxy;
	@Autowired
	Environment env;

	@Autowired
	CurrencyExchangeDao exchangeDao;
	private Logger logger= (Logger) LoggerFactory.getLogger(LimitCurrencyController.class);

	@GetMapping("limitService/{from}/currency/{to}/{quantity}")
//	@Retry(name="default",fallbackMethod = "defaultReturn")
	@RateLimiter(name="default",fallbackMethod="defaultReturn")
//	@CircuitBreaker(name="default",fallbackMethod = "defaultReturn")
	public ResponseEntity<CurrencyParameters> limitService(@PathVariable String from, @PathVariable String to,
			@PathVariable Integer quantity) {
		logger.info("Limit Service called : ");
		HashMap<String, String> idParameter = new HashMap<>();
		idParameter.put("from", from);
		idParameter.put("to", to);
//			CurrencyParameters result=new RestTemplate().getForEntity("http://localhost:8081/currencyExchange/{from}/currency/{to}",CurrencyParameters.class, idParameter).getBody();
		CurrencyParameters result = currencyInterfaceProxy.exchangeServic(from, to).getBody();
		return new ResponseEntity<CurrencyParameters>(new CurrencyParameters(from, to,
				new BigDecimal(quantity).multiply(new BigDecimal(result.getConversion_amount())),
				result.getPort()), HttpStatus.OK);

	}
	
	public ResponseEntity<String> defaultReturn(String a,String b,Integer c,Exception e) {
		return new ResponseEntity<String>(a + " " + b + " " + c + " "  +"Fallback-return",HttpStatus.INTERNAL_SERVER_ERROR);
	//http://localhost:8080/limitService/INR/currency/USD/100 Returned this value	"INR USD 100 Fallback-return"
	}

}
