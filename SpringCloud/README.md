##Spring Cloud demo


## endpoints

```yaml
ConfigServer Default Config: GET http://localhost:8024/demo-config/default

ServiceDiscovery Eureka apps: GET http://localhost:8761/eureka/apps
ServiceDiscovery: GET http://localhost:8761/eureka/apps/demo-app

DemoApp Refresh Config: POST http://localhost:8019/actuator/refresh
DemoApp: GET http://localhost:8019/cloud
DemoApp Eureka client data: GET http://localhost:8019/cloud/discovery/demo-app 
DemoApp Hystrix Cirtuit: GET http://localhost:8019/cloud/hystrix/circuit
DemoApp Hystrix Fallback: GET http://localhost:8019/cloud/hystrix/fallback
```

### Note

In order to see how hystrix works run the same endpoint several times to emulate failure
