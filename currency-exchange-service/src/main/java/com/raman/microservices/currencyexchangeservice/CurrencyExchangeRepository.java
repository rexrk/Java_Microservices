package com.raman.microservices.currencyexchangeservice;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findCurrencyExchangeByFromAndTo(String from, String to);
}
