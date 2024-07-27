package com.raman.microservices.currencyexchangeservice;

import io.github.resilience4j.retry.annotation.Retry;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private final Logger logger = Logger.getLogger(CircuitBreakerController.class.getName());

    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "fallbackRestTemplate")
//    @CircuitBreaker(name = "default", fallbackMethod = "fallbackRestTemplate")
//    @RateLimiter(name = "default")
//    @Bulkhead(name = "default")
    public String sampleApi() {
        logger.info("sampleApi called");
        ResponseEntity<String> failEntity = new RestTemplate().getForEntity("http://localhost:8080/fail", String.class);
        return failEntity.getBody();
    }

    public String fallbackRestTemplate(Exception e) {
        return "fallback rest template";
    }
}
