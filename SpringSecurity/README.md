# Spring Security

Demo showing some features of Spring security like basic authentication, 
JWT based authentication and integration with Oauth2 authorization server.

## API Reference

Full api reference can be found [here](http://localhost:8090/swagger)

## Flows

The demo shows two flows for authentication: Standard and Oauth2

### Standard flow

1. Create user with credentials(login/password) and roles(user,admin or both) 
2. Login (set credentials as basic auth token)
3. Get JWT token from response headers
4. Set JWT token to Authorization header with Bearer prefix

### Oauth Flow

As authorization server Keykloak is used. Security realm and user(with needed role) must be created

1. Request Oauth Authorization code (http://localhost:8080/auth/realms/spring/protocol/openid-connect/auth
   ?client_id=spring&response_type=code&fj8o3n7bdy1op5)
2. From this response (http://localhost:8080/*?state=fj8o3n7bdy1op5&session_state=6d54c21f-4671-4430-a3c2-757ba4a78ff1&code=c7e69914-07b4-45ad-8c42-29572b0bb7bf.6d54c21f-4671-4430-a3c2-757ba4a78ff1.0cf08976-5c98-4b52-a684-8935f34b2ab8) grab a code
3. Call Keycloak to get token (http://localhost:8080/realms/spring/protocol/openid-connect/token) with the X-WWW-FORM-URLENCODED from below
```
client_id: spring 
grant_type: authorization_code
code: c7e69914-07b4-45ad-8c42-29572b0bb7bf.6d54c21f-4671-4430-a3c2-757ba4a78ff1.0cf08976-5c98-4b52-a684-8935f34b2ab8
```
4. Use access_token from from the response body
5. Call any of the endpoints for getting "secret" data with header X-OAUTH-TOKEN and access_token as its value
6. Use JWT token from response header Authorization for further API calls


## Build and run

```
./gradlew clean build -x test

java -jar build/libs/SpringSecurity-1.0-SNAPSHOT.jar
```

or simply

```
./gradlew bootRun
```

Run tests 

```
./gradlew clean test
```

Server starts on port [8090](http://localhost:8090) 

## Note

For more clarifications regarding Oauth2 flow with Keycloak see [this article](https://www.initgrep.com/posts/java/spring/spring-security-oauth2-jwt-authentication-resource-server)
