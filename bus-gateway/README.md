## Spring Cloud Zuul

简介：
> Spring Cloud Zuul路由是微服务架构的不可或缺的一部分，提供动态路由，监控，弹性，安全等的边缘服务。Zuul是Netflix出品的一个基于JVM路由和服务端的负载均衡器。

1、简化客户端调用复杂度

在微服务架构模式下后端服务的实例数一般是动态的，对于客户端而言很难发现动态改变的服务实例的访问地址信息。因此在基于微服务的项目中为了简化前端的调用逻辑，通常会引入API Gateway作为轻量级网关，同时API Gateway中也会实现相关的认证逻辑从而简化内部服务之间相互调用的复杂度。

2、数据裁剪以及聚合

通常而言不同的客户端对于显示时对于数据的需求是不一致的，比如手机端或者Web端又或者在低延迟的网络环境或者高延迟的网络环境。
因此为了优化客户端的使用体验，API Gateway可以对通用性的响应数据进行裁剪以适应不同客户端的使用需求。同时还可以将多个API调用逻辑进行聚合，从而减少客户端的请求数，优化客户端用户体验。

3、多渠道支持

当然我们还可以针对不同的渠道和客户端提供不同的API Gateway,对于该模式的使用由另外一个大家熟知的方式叫Backend for front-end, 在Backend for front-end模式当中，我们可以针对不同的客户端分别创建其BFF，进一步了解BFF可以参考这篇文章：[Pattern: Backends For Frontends](https://samnewman.io/patterns/architectural/bff/)

4、遗留系统的微服务化改造

对于系统而言进行微服务改造通常是由于原有的系统存在或多或少的问题，比如技术债务，代码质量，可维护性，可扩展性等等。API Gateway的模式同样适用于这一类遗留系统的改造，通过微服务化的改造逐步实现对原有系统中的问题的修复，从而提升对于原有业务响应力的提升。通过引入抽象层，逐步使用新的实现替换旧的实现。

## 服务实例简介

此项目可以演示以下案例：

- 服务网关zuul配置
- 服务网关身份认证、权限认证
- 服务熔断响应
- 网关请求日志

## 服务配置说明

1.pom包配置

创建一个springboot项目，pom.xml中添加如下配置：

```xml
    <dependencies>
        <!-- spring security认证 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <!-- 将token存储在redis中 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <!-- 网关配置 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!-- spring security oauth2配置 -->
        <dependency>
            <groupId>org.springframework.security.oauth</groupId>
            <artifactId>spring-security-oauth2</artifactId>
            <version>${spring-security-oauth2.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>
```

2.配置文件

bootstrap.yml配置如下：

```yaml
server:
  port: 8769
spring:
  profiles:
    active: dev
  application:
    name: bus-gateway
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

bus-gateway-dev.yml配置如下：

```yaml
logging:
  level.com.github.bus: debug
spring:
  redis:
    # Redis服务器地址
    host: 192.168.0.6
    # Redis服务器连接端口
    port: 6379
    #Redis数据库索引（默认为0）
    database: 0
    # Redis服务器连接密码（默认为空）
    #password:
    # 连接超时时间（毫秒）
    #timeout: 0ms
    jedis:
      pool:
        # 连接池最大连接数（使用负值表示没有限制）
        max-active: 100
        # 连接池最大阻塞等待时间（使用负值表示没有限制）
        max-wait: 60000ms
        # 连接池中的最大空闲连接
        max-idle: 5
        # 连接池中的最小空闲连接
        min-idle: 0
# 网关路由配置（只要注册到eureka的服务，如果没有配置路由则服务默认访问路径是服务名称，可看bus-auth）        
zuul:
  routes:
    api-example:
      path: /api-example/**
      serviceId: bus-example-service
    api-upms:
      path: /api-upms/**
      serviceId: bus-upms-service
# 配置认证服务      
security:
  oauth2:
    client:
      access-token-uri: http://127.0.0.1:8763/bus-auth/oauth/token
      user-authorization-uri: http://127.0.0.1:8763/bus-auth/oauth/authorize
    resource:
      user-info-uri: http://127.0.0.1:8763/bus-auth/user
      prefer-token-info: true
```

3.启动类

启动类中添加`@EnableZuulProxy`注解

```java
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class BusGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusGatewayApplication.class, args);
    }
}
```

4.Configuration

配置资源服务器，控制访问权限。

> 此案例控制`/api-example/order/**`访问权限，必须认证后才可以访问。

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

5.FallbackProvider

Upms服务熔断响应配置案例代码如下：

```java
@Slf4j
@Component
public class UpmsFallbackProvider implements FallbackProvider {

    private static final String UPMS_SERVICE_DISABLE = "权限管理模块不可用";

    @Override
    public String getRoute() {
        // 设置服务名-配置文件
        return "bus-upms-service";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        // 响应内容
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() {
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public int getRawStatusCode() {
                return HttpStatus.SERVICE_UNAVAILABLE.value();
            }

            @Override
            public String getStatusText() {
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() {
                if (cause != null && cause.getMessage() != null) {
                    log.error("调用:{} 异常：{}", getRoute(), cause.getMessage());
                    return new ByteArrayInputStream(cause.getMessage().getBytes());
                } else {
                    log.error("调用:{} 异常：{}", getRoute(), UPMS_SERVICE_DISABLE);
                    return new ByteArrayInputStream(UPMS_SERVICE_DISABLE.getBytes());
                }
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
```

Auth服务熔断响应配置案例代码如下`(未完成)`：

```java
public class AuthFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        // 设置服务名
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        // 响应内容
        return null;
    }
}
```

6.ZuulFilter

- filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
    - pre：路由之前
    - routing：路由之时
    - post： 路由之后
    - error：发送错误调用
- filterOrder：过滤的顺序
- shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
- run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。

网关请求日志配置

```java
@Slf4j
@Component
public class LoggerFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("Gateway >> HTTP-URL: " + request.getRequestURL().toString());
        log.info("Gateway >> HTTP-METHOD: " + request.getMethod());
        log.info("Gateway >> OK");
//        Object accessToken = request.getParameter("token");
//        if (accessToken == null) {
//            log.warn("token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            try {
//                ctx.getResponse().getWriter().write("token is empty");
//            } catch (Exception e) {
//            }
//            return null;
//        }

        return null;
    }
}
```

