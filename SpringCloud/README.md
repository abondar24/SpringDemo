##Spring Cloud demo


## Endpoints

```yaml
ConfigServer Default Config: GET http://localhost:8024/demo-config/default

ServiceDiscovery Eureka apps: GET http://localhost:8761/eureka/apps
ServiceDiscovery: GET http://localhost:8761/eureka/apps/demo-app

Service GateWay: GET http://localhost:8765/actuator/routes
Service GateWay DemoApp: GET http://localhost:8765/api/demo-app/<any-demo-app-endpoint>

DemoApp Refresh Config: POST http://localhost:8019/actuator/refresh
DemoApp: GET http://localhost:8019/cloud
DemoApp Eureka client data: GET http://localhost:8019/cloud/discovery/demo-app 
DemoApp Hystrix Cirtuit: GET http://localhost:8019/cloud/hystrix/circuit
DemoApp Hystrix Fallback: GET http://localhost:8019/cloud/hystrix/fallback

DemoApp send message to Kafka client: PUT http://localhost:8019/cloud/stream  
PUT BODY: message text
PUT Response: {
              "body": "dgdfgdgdfg",
              "id": "d030eab1-e1a7-4429-bbbe-e99f6d1b4440"
          }
```

### Note

In order to see how hystrix works run the same endpoint several times to emulate failure
