## Eureka简介

按照官方介绍：
> Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of middle-tier servers.
> 
> Eureka 是一个基于 REST 的服务，主要在 AWS 云中使用, 定位服务来进行中间层服务器的负载均衡和故障转移。

Finchley版本的官方文档如下：

> http://cloud.spring.io/spring-cloud-static/Finchley.RELEASE/single/spring-cloud.html

- Spring Cloud 封装了 Netflix 公司开发的 Eureka 模块来实现服务注册和发现。Eureka 采用了 C-S 的设计架构。Eureka Server 作为服务注册功能的服务器，它是服务注册中心。而系统中的其他微服务，使用 Eureka 的客户端连接到 Eureka Server，并维持心跳连接。这样系统的维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。Spring Cloud 的一些其他模块（比如Zuul）就可以通过 Eureka Server 来发现系统中的其他微服务，并执行相关的逻辑。
    
- Eureka由两个组件组成：Eureka服务器和Eureka客户端。Eureka服务器用作服务注册服务器。Eureka客户端是一个java客户端，用来简化与服务器的交互、作为轮询负载均衡器，并提供服务的故障切换支持。Netflix在其生产环境中使用的是另外的客户端，它提供基于流量、资源利用率以及出错状态的加权负载均衡。

用一张图来认识以下：

![img](http://www.itmind.net/assets/images/2017/springcloud/eureka-architecture-overview.png)

上图简要描述了Eureka的基本架构，由3个角色组成：

1.Eureka Server
- 提供服务注册和发现

2.Service Provider
- 服务提供方将自身服务注册到Eureka，从而使服务消费方能够找到

3.Service Consumer
- 服务消费方从Eureka获取注册服务列表，从而能够消费服务
## 服务实例简介

此项目可以演示以下案例：

- 服务注册中心

## 服务配置说明

spring cloud已经帮我实现了服务注册中心，我们只需要很简单的几个步骤就可以完成。

1.pom中添加依赖

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-eureka-server</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>
```

2.配置文件

在默认设置下，该服务注册中心也会将自己作为客户端来尝试注册它自己，所以我们需要禁用它的客户端注册行为，在`application.yml`添加以下配置：
```yaml
spring:
  application:
    name: bus-center
server:
  port: 8761
eureka:
  instance:
    prefer-ip-address: true
  client:
    registerWithEureka: false
    fetchRegistry: false
    #serviceUrl.defaultZone: http://${eureka.instance.hostname}:${configurer.port}/eureka/    
```
- eureka.client.registerWithEureka ：表示是否将自己注册到Eureka Server，默认为true。
- eureka.client.fetchRegistry ：表示是否从Eureka Server获取注册信息，默认为true。
- eureka.client.prefer-ip-address ：表示Eureka Server是否自动获取设备的IP
- eureka.client.serviceUrl.defaultZone ：设置与Eureka Server交互的地址，查询服务和注册服务都需要依赖这个地址。默认是http://localhost:8761/eureka ；多个地址可使用 , 分隔。

3.启动类

启动类中添加`@EnableEurekaServer`注解

```java
@EnableEurekaServer
@SpringBootApplication
public class CenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenterApplication.class, args);
    }
}
```



启动工程后，访问：[http://localhost:8761/](http://localhost:8761/)，Eureka页面，其中还没有发现任何服务