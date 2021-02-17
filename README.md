# Java distributed system
------------------------
This repository is an example of distributed system using spring cloud and spring boot 

## Services :
--------------------
- Config  service (spring cloud config) 
- Gateway service (spring netflix zuul api gateway)
- Naming  service (spring netflix eureka naming)
- Users   service (JWT , Rabbit mq and Spring security )
- Tweets   service (Rabbit mq)


## Run all services : 
------------------------
```
cd config-service
./mvnw spring-boot:run
cd ..
cd user-service
./mvnw spring-boot:run
cd ..
cd tweets-service
./mvnw spring-boot:run
cd ..
cd gatewayservice
./mvnw spring-boot:run
cd ..
docker-compose  down
docker-compose  up

```


