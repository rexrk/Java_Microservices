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
    port : server.port={port}

- Clients Configurations : 
    > Dependency : spring-cloud-starter-config <br>
    > props : spring.config.import=optional:configserver:http://localhost:8888

- Multiple Profiles in app.props (activate a profile):
    > spring.profiles.active={name} <br> spring.cloud.config.profile={name}


## Day 4
- Currency Exchange Service (Microservice Example) : <br>
    - links : 
        - http://localhost:8000/currency-exchange/from/USD/to/INR

    - Fetch environment values from a class
        > import org.springframework.core.env.Environment

    - Run multiple Instance of java app on different ports
        > add vm options in run config : [-Dserver.port={port}]
    

- Currency Conversion Service (connect with exchange service) : <br>
    - links : 
        - http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
    
    - Get REst Data from different microservice via URI :
        > RestTemplate rt = new RestTemplate().{methods};

    - ### OpenFeign Rest Client for service invocation (Optimize Rest Template) :
        > dependency : spring-cloud-starter-openfeign <br>
        Main class : @EnableFeignClients <br>
        Proxy Interface : @FeignClient(name={spring.app.name}, url={url})

## Day 5
- Naming server configs (Registry for microservices name):
    > Dependency : spring-cloud-starter-netflix-eureka-client <br>
    > Main class : @EnableEurekaServer
    - Disable self discovery of server
        > eureka.client.register-with-eureka=false <br>
        eureka.client.fetch-registry=false

- Eureka Clients (service discovery) :
    > eureka.client.service-url.defaultZone=http://localhost:8761/eureka

## Day 6
- Load Balancing on client side (feign) 
    - remove url in @FeignClient for auto load balancing





