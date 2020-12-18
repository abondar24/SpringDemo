#Spring Hazelcast

Example of spring caching using hazelcast cache manager.


## Endpoint

```
POST localhost:8080/cache
Body: {
 "name":"Ale2x",
"phoneNumber":"546546456546"
}

GET localhost:8080/cache/{id}
```

## Build and run

```
gradle clean build

java -jar build/libs/hazelcast-1.0-SNAPSHOT.jar 
```
Or
```
gradle clean build

gradlew bootJar run
```
## Note

You should see the difference of response time for get requests.
