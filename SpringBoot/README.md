## SpringBoot Demo

Small spring boot demo with rest and security

### Endpoint Documentation
Swagger ui: localhost:8080/swagger-ui.html

Swagger.json: localhost:8080/v2/api-docs

### Build and Run
```yaml
mvn clean install spring-boot:run
```

or 

```yaml
mvn clean install

java -jar /target/<jarname>.jar
```

### Hints

- In order to login use the following body

```yaml
{
	"username":"vasya",
	"password": "password"
	
}
```
- In order to get exception in /demo/ex endpoint use Arsen as parameter 
