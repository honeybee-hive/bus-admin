/*
 * BaseException  1.0  2018-10-15
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.common.exception;

/**
 * 基础异常类
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-15 zy 初版
 */
public class BaseException extends RuntimeException {

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
