###Spring boot Kafka

Small demo with kafka producer and consumer

## Requirements
Kafka server with topic "spring-demo"

## Endpoints
```yaml
PUT localhost:8080/send : send message to Kafka. Message text must be put to body

GET localhost:8080/messages : get messages consumed by consumer

GET localhost:8080/health : get broker status
```

## Build and run
```yaml
mvn clean install spring-boot:run
```

or 

```yaml
mvn clean install

java -jar /target/<jarname>.jar
```
