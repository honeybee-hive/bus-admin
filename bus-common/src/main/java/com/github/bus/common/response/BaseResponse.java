/*
 * Result  1.0  2018-10-09
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.common.response;

import lombok.Data;

/**
 * 返回结果
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-09 zy 初版
 */
@Data
public class BaseResponse {

    private String code;

    private String message;

    public BaseResponse() {
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseResponse getInstance(String code, String message) {
        return new BaseResponse(code, message);
    }
}
