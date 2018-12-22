/*
 * GlobalExceptionHandler  1.0  2018-10-15
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.upms.exception;

import com.github.bus.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常拦截器
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-15 zy 初版
 */
@Slf4j
@ControllerAdvice("com.github.bus.upms")
@ResponseBody
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse exceptionHandler(Exception ex) {
//        log.error(ex.getMessage(), ex);
        ex.printStackTrace();
        return BaseResponse.getInstance(HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage());
    }
}
