    ## Progress Track

# Day 1
```
Config Client Config Client Config Client
           \1        |2        /3
                Config Server
```

# Day 2
- Fetching values from application properties to a class.
    > @ConfigurationProperties("{name}")

- Centralized Configuration of Microservice
    > Spring Cloud Config Server

- Adding git to config server application

# Day 3
- Clients Configurations : 
    > spring.config.import=optional:configserver:http://localhost:8888

- Server Configurations : 
    > port : {port}
    > spring.cloud.config.server.git.uri=file:///{path}

- Multiple Profiles for properties : 
    > spring.profiles.active={name} <br> spring.cloud.config.profile={name}


# Day 4
- Currency Exchange Service (Microservice Example) : <br>
    - links : 
        - http://localhost:8000/currency-exchange/from/USD/to/INR

    - Fetch environment values from a class
        > import org.springframework.core.env.Environment

    - Run multiple Instance of java app on different ports
        > add vm options in run config : [-Dserver.port={port}]
    

- Currency Conversion Service (Microservice Example) : <br>
    - links : 
        - http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
    
    - Get Data from different microservice via URI :
        > RestTemplate rt = new RestTemplate().{methods};
    

