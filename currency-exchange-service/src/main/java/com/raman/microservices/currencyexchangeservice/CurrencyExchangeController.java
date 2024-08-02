package com.raman.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue( @PathVariable String from,
                                                   @PathVariable String to) {
//        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        logger.info("Retrieving exchange value from {} to {}", from, to);
        CurrencyExchange currencyExchange = repository.findCurrencyExchangeByFromAndTo(from, to);
        if(currencyExchange == null) {throw new RuntimeException("No data found for " + from + " to " + to);}
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;

    }

    // INFO [currency-exchange,30be168ed1febba9b3f5b9731ca3e46b,159d27b65509510e]
}
