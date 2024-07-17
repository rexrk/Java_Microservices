package com.raman.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private Environment env;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity

    ) {
        Map<String, String> uriVariables = new HashMap<>();
        String url = String.format("http://localhost:8000/currency-exchange/from/%s/to/%s", from, to);
        String port = env.getProperty("local.server.port");

        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(url, CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();

        assert currencyConversion != null;
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));

        return currencyConversion;

    }
}