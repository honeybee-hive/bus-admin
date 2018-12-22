## Spring Boot Admin

简介

- Spring Boot Admin是一个用于管理和监视SpringBoot的应用程序。应用程序注册到我们的SpringBootAdmin客户端(通过HTTP)，或者使用SpringCloud(例如：Eureka，Consul)发现。UI只是SpringBootActuator端点之上的AngularJs应用程序。

- Spring Boot Admin分为admin-server与admin-client两个组件，admin-server通过采集actuator端点数据，显示在spring-boot-admin-ui上，通过spring-boot-admin可以动态切换日志级别、导出日志、导出heapdump、监控各项指标等等。

- Spring Boot Admin在对单一应用服务监控的同时也提供了集群监控方案，支持通过eureka、consul、zookeeper等注册中心的方式实现多服务监控与管。

参考文档：[http://codecentric.github.io/spring-boot-admin/current/](http://codecentric.github.io/spring-boot-admin/current/)

## 服务实例简介

此项目可以演示以下案例：

- 管理和监视SpringBoot的应用程序
- 配置用户登陆

## 服务配置说明

spring cloud已经帮我实现了服务注册中心，我们只需要很简单的几个步骤就可以完成。

1.pom包配置

建一个springboot项目，pom.xml中添加如下配置：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
    <!--spring boot admin 服务-->
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-server</artifactId>
    </dependency>
</dependencies>
```

2.配置文件

bootstrap.yml配置如下：

```yaml
server:
  port: 8000
spring:
  application:
    name: bus-app-monitor
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
    leaseRenewalIntervalInSeconds: 10
    health-check-url-path: /actuator/health
  client:
    registryFetchIntervalSeconds: 5
    serviceUrl:
      defaultZone: http://127.0.0.1:8761/eureka/
```

bus-app-monitor-dev.yml配置如下：

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: ALWAYS
#配置用户登陆      
spring:
  security:
    user:
      name: "user"
      password: "password"
eureka:
  instance:
    metadata-map:
      user.name: ${spring.security.user.name}         #These two are needed so that the configurer
      user.password: ${spring.security.user.password} #can access the protected client endpoints
```

3.启动类

启动类中添加`@EnableAdminServer`注解

```java
@EnableAdminServer
@EnableEurekaClient
@SpringBootApplication
public class AppMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppMonitorApplication.class, args);
    }

}
```

4.Configuration

>`SecuritySecureConfig.java`配置登陆页面允许访问

```java
@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");

        http.authorizeRequests()
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                .logout().logoutUrl(adminContextPath + "/logout").and()
                .httpBasic().and()
                .csrf().disable();
        // @formatter:on
    }
}
```

启动成功后，访问[http://localhost:8000](http://localhost:8000)，进入登陆页面，成功后显示监测页面。