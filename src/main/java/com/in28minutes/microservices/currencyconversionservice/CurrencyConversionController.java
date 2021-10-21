package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String hostname = System.getenv("HOSTNAME");
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionRest(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
				
		String url = "http://currency-exchange:8000/currency-exchange/from/{from}/to/{to}";
		Map<String, String> pathParam = new HashMap<>();
		pathParam.put("from", from);
		pathParam.put("to", to);
		ResponseEntity<CurrencyConversion> response = restTemplate.getForEntity(url, CurrencyConversion.class,
				pathParam);
		CurrencyConversion currencyConversion = response.getBody();
		currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		currencyConversion.setEnvironment(currencyConversion.getEnvironment() + " rest, HOSTNAME:: " + hostname);
		return currencyConversion;
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
				
		CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConversion(currencyConversion.getId(), 
				from, to, quantity, 
				currencyConversion.getConversionMultiple(), 
				quantity.multiply(currencyConversion.getConversionMultiple()), 
				currencyConversion.getEnvironment() + " feign, HOSTNAME:: " + hostname);
	}

	@GetMapping("/currency-conversion")
	public String calculateCurrencyConversionRest() {
			return "Hello this is currency-conversion service!!!";
	}
}
