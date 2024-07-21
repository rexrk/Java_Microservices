<h1> Progress Track

## Day 1

```
Config Client | Config Client | Config Client
           \1        |2         /3
               +-------------+
               |Config Server|
               +-------------+
```

## Day 2

- Fetching values from application properties to a class variables.

  > @ConfigurationProperties("{name}")

- Centralized Configuration of Microservice server

  > Dependency : Spring-Cloud-Config-Server

- Adding git to config server application.
  - higher priority

## Day 3

- Server Configurations :

  > spring.cloud.config.server.git.uri=file:///{Current directory Path} <br>
  > port : server.port={port}

- Clients Configurations :

  > Dependency : spring-cloud-starter-config <br>
  > props : spring.config.import=optional:configserver:http://localhost:8888

- Multiple Profiles in app.props (activate a profile):
  > spring.profiles.active={name} <br> spring.cloud.config.profile={name}

## Day 4

- Currency Exchange Service (Microservice Example) : <br>

  - link : http://localhost:8000/currency-exchange/from/USD/to/INR

  - Fetch environment values from a class

    > import org.springframework.core.env.Environment

  - Run multiple Instance of java app on different ports
    > add vm options in run config : [-Dserver.port={port}]

- Currency Conversion Service (connect with exchange service) : <br>

  - link : http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

  - Get REst Data from different microservice via URI :
    > RestTemplate rt = new RestTemplate().{methods};

  - ### OpenFeign Rest Client for service invocation (Optimize Rest Template) :
    > dependency : spring-cloud-starter-openfeign <br>
    > Main class : @EnableFeignClients <br>
    > Proxy Interface : @FeignClient(name={spring.app.name}, url={url})
    - link : http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

## Day 5

- Eureka Naming server (Registry for microservices name):

  > Dependency : spring-cloud-starter-netflix-eureka-client <br>
  > Main class : @EnableEurekaServer

  - Disable self discovery of server
    > eureka.client.register-with-eureka=false <br> eureka.client.fetch-registry=false
  - link : http://localhost:8761

- Eureka Clients (service discovery) :
  > eureka.client.service-url.defaultZone=http://localhost:8761/eureka

## Day 6

- Load Balancing on client side (feign)
  - remove url in @FeignClient(url=?) for auto load balancing.

- Api Gateway Discovery Locator (authentication, authorization, logging, rate limiting)

  > Dependency : spring-cloud-starter-gateway <br>
  > props : spring.cloud.gateway.discovery.locator.enabled=true

- Links (Initial) :
  - http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/USD/to/INR
  - http://localhost:8765/CURRENCY-CONVERSION/currency-conversion/from/USD/to/INR/quantity/10
  - http://localhost:8765/CURRENCY-CONVERSION/currency-conversion-feign/from/EUR/to/INR/quantity/10

- Fixing uppercase letters in links :

  > props : spring.cloud.gateway.discovery.locator.enabled=true

  - New Links (lowercase) :
    - http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR
    - http://localhost:8765/currency-conversion/currency-conversion/from/USD/to/INR/quantity/10
    - http://localhost:8765/currency-conversion/currency-conversion-feign/from/EUR/to/INR/quantity/10

- Routes with Spring Cloud Gateway

1. Define a @Configuration Class
2. Define a @Bean of return type `RouteLocator` with parameters `(RouteLocatorBuilder builder)`
3. Define routes :

```java
  return builder.routes()
                .route(p -> p
                        .path("/get")
                //GatewayFilterSpec filters
                        .filters(f -> f
                                .addRequestHeader("{MyHeader}", "{MyURI}")
                                .addRequestParameter("{MyParam}", "{MyValue}"))
                        .uri("http://httpbin.org/get"))
                //predicate
                .route(p -> p
                        .path("/{path}/**")
                        .uri("lb://{path}"))
                //for rewriting paths
                .route(p -> p
                        .path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath("currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion"))
                .build(); 

```
- links : 
    - http://httpbin.org/get
    - http://localhost:8765/currency-exchange/from/USD/to/INR
    - http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10
    - http://localhost:8765/currency-conversion-feign/from/EUR/to/INR/quantity/10
    - http://localhost:8765/currency-conversion-new/from/AUD/to/INR/quantity/110
  
## Day 7

- Spring Cloud Gateway Logging Filter
  > class : implements gateway.filter.GlobalFilter 
  > implement method : Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)

