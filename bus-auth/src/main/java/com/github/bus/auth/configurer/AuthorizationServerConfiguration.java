/*
 * AuthorizationServerConfiguration  1.0  2018-10-23
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.auth.configurer;

import com.github.bus.common.constant.ResourceConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 授权服务器
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-23 zy 初版
 */
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
