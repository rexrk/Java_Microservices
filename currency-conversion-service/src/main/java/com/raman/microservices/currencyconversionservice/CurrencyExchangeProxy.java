package com.raman.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "currency-exchange", url = "http://localhost:8080")
public interface CurrencyExchangeProxy {

}
