## 认证服务

使用oauth2保护你的应用，可以分为三个步骤

- 配置资源服务器
- 配置认证服务器
- 配置spring security

前两点是oauth2的主体内容，spring security oauth2是建立在spring security基础之上的，所以有一些体系是公用的。

oauth2根据使用场景不同，分成了4种模式

- 授权码模式（authorization code）
- 简化模式（implicit）
- 密码模式（resource owner password credentials）
- 客户端模式（client credentials）

学习资料：[理解OAuth 2.0 - 阮一峰的网络日志](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html)

## 服务实例简介

此项目可以演示以下案例：

- OAuth2认证服务

## 服务配置说明

spring cloud已经帮我实现了服务注册中心，我们只需要很简单的几个步骤就可以完成。

1.pom包配置

建一个springboot项目，pom.xml中添加如下配置：

```yaml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>        
        <!--spring security-->
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
        <!-- eureka 客户端-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--spring security oauth2-->
        <dependency>
            <groupId>org.springframework.security.oauth</groupId>
            <artifactId>spring-security-oauth2</artifactId>
            <version>${spring-security-oauth2.version}</version>
        </dependency>
        <!-- 内部包引用 -->
        <dependency>
            <groupId>com.github.bus</groupId>
            <artifactId>bus-common</artifactId>
            <version>${bus.version}</version>
        </dependency>
    </dependencies>
```

2.配置文件

bootstrap.yml配置如下：

```yaml
server:
  port: 8763
spring:
  profiles:
    active: dev
  application:
    name: bus-auth
  # 配置服务
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

bus-auth-dev.yml配置如下：

```yaml
logging:
  level.com.github.bus: debug
#redis配置(token存储在redis)
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
```

3.启动类

```java
@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
```

4.Configuration

> 配置授权服务`AuthorizationServerConfiguration.java`添加注解`@EnableAuthorizationServer`并继承`AuthorizationServerConfigurerAdapter`

```java
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    //认证管理
    @Autowired
    private AuthenticationManager authenticationManager;
    
    //存储方式
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单认证
        security.allowFormAuthenticationForClients();
    }

    /**
     * 配置OAuth2的客户端信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        // 加密方式
        String finalSecret = new BCryptPasswordEncoder().encode("123456");
        // 配置一个用于password认证,clientId=app,clientSecret=123456,GrantType=password
        clients.inMemory().withClient("app")
                .resourceIds(ResourceConstant.DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("client")
                .secret(finalSecret)
                .accessTokenValiditySeconds(60 * 30)
                .refreshTokenValiditySeconds(60 * 60);
    }

    /**
     * 配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                //存储方式
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                //支持请求的类型
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

}
```

> 配置spring security`WebSecurityConfiguration.java` 添加注解`@EnableWebSecurity`并继承`WebSecurityConfigurerAdapter`

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        // 使用内存方式添加两个客户信息
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String finalPassword = bCryptPasswordEncoder.encode("123456");
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
        manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
        return manager;
    }

    // 用 BCrypt 对密码加密方式
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                //允许访问授权地址
                .antMatchers("/oauth/**").permitAll();
        // @formatter:on
    }
}
```

5.Controller

> UserController.java增加此类访问Principal

```java
@RestController
public class UserController {
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
```

进行如上配置之后，启动springboot应用就可以发现多了一些自动创建的endpoints：

```text
{[/oauth/authorize]}    
{[/oauth/authorize],methods=[POST]}  
{[/oauth/token],methods=[GET]}  
{[/oauth/token],methods=[POST]}     
{[/oauth/check_token]}  
{[/oauth/error]}
```