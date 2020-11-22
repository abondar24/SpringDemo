##Spring HTTP Invoker

Usage of remote HTTP service. Demo consists of two parts: server and client.

Server is a small web application. Client is a small command line application.

###Build and run

####Server
- Build
```yaml
mvn clean install -Pserver
```

- Deploy to Tomcat

- Access via http://localhost:8080/SpringHTTPInvoker/remoting/ContactService

####Client
````yaml
mvn clean install -Pclient

java -jar <jar-location>/spring-invoker.jar
````
