## 服务实例简介

此项目可以演示以下案例：

- 服务提供注册
- spring boot admin 客户端监控
- 服务消费feign（访问项目：bus-upms-service-api）
- 服务配置中心
- 链路跟踪
- 熔断器聚合监控
- 集成swagger2

## 服务配置说明

1.pom包配置

创建一个springboot项目，pom.xml中添加如下配置：
```xml
    <dependencies>
        <!-- 监控接口 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!-- 属性配置 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
        </dependency>
        <!-- 服务提供配置 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!-- 配置中心 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <!-- 服务调用 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!-- 链路跟踪 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
        <!-- 熔断器 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <!-- 公共模块引用 -->
        <dependency>
            <groupId>com.github.bus</groupId>
            <artifactId>bus-common</artifactId>
            <version>${bus.version}</version>
        </dependency>
        <!-- 服务提供API -->
        <dependency>
            <groupId>com.github.bus.modules</groupId>
            <artifactId>bus-upms-service-api</artifactId>
            <version>${bus.version}</version>
        </dependency>
    </dependencies>
```

2.配置文件

bootstrap.yml配置如下：
```yaml
server:
  port: 8856
spring:
  profiles:
    active: dev
  application:
    name: bus-example-service
  # 从服务配置中心获取配置信息
  cloud:
    config:
      # 指明配置文件
      profile: ${spring.profiles.active}
      # 指明远程仓库的分支
      label: master
      discovery:
        enabled: true
        # 指明配置服务中心的serviceId（都是在Eureka服务中心寻找serviceId）
        serviceId: bus-config
eureka:
  instance:
    prefer-ip-address: true
  client:
    serviceUrl:
      defaultZone: http://127.0.0.1:8761/eureka/
```

http请求地址和资源文件映射如下:
```text
- /{application}/{profile}[/{label}]
- /{application}-{profile}.yml
- /{label}/{application}-{profile}.yml
- /{application}-{profile}.properties
- /{label}/{application}-{profile}.properties
```

bus-example-service-dev.yml配置如下：
```yaml
spring:
  #链路跟踪服务器地址
  zipkin:
    base-url: http://192.168.99.100:9411
  sleuth:
    sampler:
      # 全部监控（百分比）
      percentage: 1.0
logging:
  level.com.github.bus: debug
#默认为false，如果想用断路由，要打开这个设置
feign:
  hystrix:
    enabled: true
#spring boot admin monitor（监测访问权限配置）
management:
  endpoints:
    web:
      exposure:
        include: '*'
      #跨域
      cors:
        allowed-origins: "*"
        allowed-methods: "*"
  endpoint:
    health:
      show-details: ALWAYS
  security:
    enabled: false
#实例：spring cloud config 配置中心通过Git获取参数信息    
foo: hello world!
```

3.启动类

启动类中添加`@EnableSwagger2` `@EnableFeignClients` `@SpringCloudApplication`注解，其中`@SpringCloudApplication`包含`@EnableDiscoveryClient`与`@EnableCircuitBreaker`
```java
@EnableSwagger2
@EnableFeignClients
@SpringCloudApplication
public class ExampleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleServiceApplication.class, args);
    }
}
```

4.controller

- `HelloController.java`提供`/hi`、`/testHystric`服务

> HelloController.java代码如下：
```java
@Slf4j
@RestController
@RequestMapping("/example")
public class HelloController extends BaseController {

    @Value("${server.port}")
    String port;
    //演示配置服务中心参数
    @Value("${foo}")
    String foo;

    @Autowired
    private FeignUserService feignUserService;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ApiOperation(value = "演示hello world!")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        String message = foo + name + " ,i am from port:" + port;
        log.info(message);
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/testHystric", method = RequestMethod.GET)
    @ApiOperation(value = "演示断路器及链路跟踪", notes = "说明", response = ObjectResponse.class)
    public ObjectResponse testHystric(@RequestParam(value = "id") String id) {
        log.info("user get method is being called;");
        return feignUserService.get(id);
    }

}
```

- `TestEndpoints.java`提供/product/{id}与/order/{id}服务演示OAuth2密码认证方式。详情查看bus-auth与bus-gateway项目，bus-auth是认证服务器配置`AuthorizationServerConfiguration.java`、`WebSecurityConfiguration.java`，bus-gateway是资源服务器配置`ResourceServerConfiguration.java`。

> TestEndpoints.java代码如下：
```java
@Slf4j
@RestController
public class TestEndpoints {

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }
}
```

- `ResourceServerConfiguration.java`配置访问权限

> ResourceServerConfiguration.java代码如下：
```java
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("order").stateless(true).tokenStore(new RedisTokenStore(redisConnectionFactory));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api-example/order/**").authenticated();//配置order访问控制，必须认证过后才可以访问
    }
}
```

5.feign调用实现

- `FeignUserService.java`继承UserService.java，配置`@FeignClient`注解参数name（服务名）与fallback（断路器配置指定类）

> FeignUserService.java代码如下：
```java
@FeignClient(name = "bus-upms-service", fallback = FeignUserServiceFallback.class)
public interface FeignUserService extends UserService {
}
```
> FeignUserServiceFallback.java代码如下：
```java
@Component
public class FeignUserServiceFallback implements FeignUserService {
    @Override
    public ObjectResponse get(String id) {
        return ObjectResponse.getInstance(ResponseStatus.NO_LOSE_SERVICE.value(), ResponseStatus.NO_LOSE_SERVICE.getMessage());
    }
}
```

> UserService.java代码如下：
```java
public interface UserService {
    @ResponseBody
    @RequestMapping(value = "/user/get", method = RequestMethod.GET)
    public ObjectResponse get(@RequestParam(value = "id") String id);
}
```
