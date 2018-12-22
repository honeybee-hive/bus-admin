## 服务实例简介

此项目可以演示以下案例：

- 服务配置注册
- 服务配置中心

## 服务配置说明

1.pom包配置

创建一个springboot项目，pom.xml中添加如下配置：
```xml
    <dependencies>
        <!-- 配置服务 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <!-- eureka 客户端-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
    </dependencies>
```

2.配置文件

application.yml配置如下：
```yaml
server:
  port: 8762
spring:
  application:
    name: bus-config
  cloud:
    config:
      server:
        git:
          uri: http://192.168.0.123:7997/malt/bus-admin.git    # git仓库的地址
          search-paths: /config-yml                            # git仓库地址下的相对地址，可以配置多个，用,分割。
          username: config                                     # git仓库的账号
          password: config123                                  # git仓库的密码
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

http请求地址和资源文件映射如下:
```text
- /{application}/{profile}[/{label}]
- /{application}-{profile}.yml
- /{label}/{application}-{profile}.yml
- /{application}-{profile}.properties
- /{label}/{application}-{profile}.properties
```

3.启动类

启动类中添加`@EnableConfigServer`注解

```java
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class BusConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusConfigApplication.class, args);
    }
}
```