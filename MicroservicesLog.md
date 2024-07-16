    ## Progress Track

Day 1
```
Config Client Config Client Config Client
           \1        |2        /3
                Config Server
```

Day 2
- Fetching values from application properties to a class.
    > @ConfigurationProperties("{name}")

- Centralized Configuration of Microservice
    > Spring Cloud Config Server

- Adding git to config server application

Day 3
- Clients Configurations : 
    > spring.config.import=optional:configserver:http://localhost:8888

- Server Configurations : 
    > port : {port}
    > spring.cloud.config.server.git.uri=file:///{path}

- Multiple Profiles for properties : 
    > spring.profiles.active={name} <br> spring.cloud.config.profile={name}
    

