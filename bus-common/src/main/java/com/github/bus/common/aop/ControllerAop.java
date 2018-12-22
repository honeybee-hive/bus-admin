/*
 * ControllerAop  1.0  2018-10-15
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 全局请求日志输出记录
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-15 zy 初版
 */
@Slf4j
@Aspect
@Component
public class ControllerAop {

    private static final String CONTROLLER_POINT_CUT = "@annotation (org.springframework.web.bind.annotation.RequestMapping)";

    @Autowired
    private HttpServletRequest request;

    @Around(value = CONTROLLER_POINT_CUT)
    public Object methodAround(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();

        // 获取当前的线程号
        Long threadId = Thread.currentThread().getId();

        String identify = "[ThreadId-" + threadId + "]";
        // 记录下请求内容
        log.info(identify + "HTTP-SESSION-ID: " + request.getSession().getId());
        log.info(identify + "HTTP-URL: " + request.getRequestURL().toString());
        log.info(identify + "HTTP-METHOD: " + request.getMethod());
        log.info(identify + "CLASS-METHOD: " + pjp.getSignature().getDeclaringTypeName() + "."
                + pjp.getSignature().getName());
        log.info(identify + "CLASS-METHOD-ARGS: " + Arrays.toString(pjp.getArgs()));

        Object result;
        result = pjp.proceed();
        log.info(identify + "PROCESS-TIME: " + (System.currentTimeMillis() - startTime) + "ms");

        return result;
    }
}
