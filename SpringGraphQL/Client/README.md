# Demo GraphQL Client

The client is based on Apollo-Runtime library.

Currently there are some issues with classes generation and the client is not operational.
So it is put here for reference purpose.

If someone is familiar with another Java GraphQL client solution don't hesitate to contact me.



# Build and run 

- Generate classes for queries and mutations

```yaml
mvn clean generate-sources
``` 

- Build and run

```yaml
mvn clean install

java -jar target/graph-client.jar
```