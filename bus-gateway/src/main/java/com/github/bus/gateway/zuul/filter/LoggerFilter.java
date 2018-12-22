/*
 * LoggerFilter  1.0  2018-10-17
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.gateway.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关请求日志
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-17 zy 初版
 */
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
