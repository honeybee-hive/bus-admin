/*
 * AuthFallbackProvider  1.0  2018-10-17
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.gateway.zuul.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.client.ClientHttpResponse;

/**
 * 认证服务熔断响应
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-17 zy 初版
 */
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
