package com.github.bus.common.constant;

/**
 * 系统返回码
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-08 zy 初版
 */
public enum ResponseStatus {

    OK("0", "正常"),
    NO_FOUND("9001", "没有找到"),
    NO_LOSE_SERVICE("9002", "缺少的服务器"),
    ERROR("9999", "内部错误");

    private final String code;

    private final String message;

    private ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String value() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
