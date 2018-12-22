/*
 * UpmsFallbackProvider  1.0  2018-10-17
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.gateway.zuul.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * UPMS服务熔断响应
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-17 zy 初版
 */
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
