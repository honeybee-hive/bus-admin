## 熔断监控Hystrix Dashboard和Turbine

简介

> Turbine是聚合服务器发送事件流数据的一个工具，用来监控集群下hystrix的metrics情况。

## 服务实例简介

此项目可以演示以下案例：

- Hystrix仪表板
- Turbine

## 服务配置说明

1.pom包配置

建一个springboot项目，pom.xml中添加如下配置：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <!-- Hystrix仪表板 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
    </dependency>
    <!-- turbine -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
</dependencies>
```

2.配置文件

bootstrap.yml配置如下：

```yaml
spring:
  application:
    name: service-turbine-monitor
  profiles:
    active: dev
  cloud:
    config:
      profile: ${spring.profiles.active}
      label: master
      discovery:
        enabled: true
        serviceId: bus-config
eureka:
  instance:
    prefer-ip-address: true
  client:
    serviceUrl:
      defaultZone: http://127.0.0.1:8761/eureka/
```

bus-turbine-monitor-dev.yml配置如下：

```yaml
server:
  port: 8001
turbine:
  app-config: bus-upms-service,bus-example-service
  aggregator:
    clusterConfig: default
  clusterNameExpression: new String("default")
  combine-host: true
  instanceUrlSuffix:
    default: actuator/hystrix.stream
#spring boot admin monitor
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: ALWAYS
```
- turbine.appConfig ：配置Eureka中的serviceId列表，表明监控哪些服务
- turbine.aggregator.clusterConfig ：指定聚合哪些集群，多个使用”,”分割，默认为default。可使用http://.../turbine.stream?cluster={clusterConfig之一}访问
- turbine.clusterNameExpression ： 
    - 1.clusterNameExpression指定集群名称，默认表达式appName；此时：turbine.aggregator.clusterConfig需要配置想要监控的应用名称；
    - 2.当clusterNameExpression: default时，turbine.aggregator.clusterConfig可以不写，因为默认就是default；
    - 3.当clusterNameExpression: metadata[‘cluster’]时，假设想要监控的应用配置了eureka.instance.metadata-map.cluster: ABC，则需要配置，同时turbine.aggregator.clusterConfig: ABC

3.启动类

启动类中添加`@EnableTurbine`注解，激活对Turbine的支持，添加`@EnableHystrixDashboard`注解，激活Hystrix仪表板

```java
@EnableTurbine
@SpringBootApplication
@EnableHystrixDashboard
public class TurbineMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineMonitorApplication.class, args);
    }
}
```
启动成功后，访问[http://localhost:8001/hystrix](http://localhost:8001/hystrix)显示Hystrix Dashboard页面

图中会有一些提示：

> Cluster via Turbine (default cluster): http://turbine-hostname:port/turbine.stream

> Cluster via Turbine (custom cluster): http://turbine-hostname:port/turbine.stream?cluster=[clusterName]

> Single Hystrix App: http://hystrix-app:port/hystrix.stream

大概意思就是如果查看默认集群使用第一个url,查看指定集群使用第二个url,单个应用的监控使用最后一个，我们暂时只演示单个应用的所以在输入框中输入： http://localhost:8001/hystrix.stream ，输入之后点击 monitor，进入页面。