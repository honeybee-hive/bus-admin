/*
 * Result  1.0  2018-10-08
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.common.response;

import com.github.bus.common.constant.ResponseStatus;
import lombok.Data;

/**
 * 结果对象
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-08 zy 初版
 */
@Data
public class ObjectResponse<T> extends BaseResponse {

    private T value;

    public ObjectResponse(String code, String message, T value) {
        super(code, message);
        this.value = value;
    }

    public static ObjectResponse getInstance(ResponseStatus responseStatus) {
        return new ObjectResponse(responseStatus.value(),responseStatus.getMessage(), null);
    }

    public static ObjectResponse getInstance(ResponseStatus responseStatus) {
        return new ObjectResponse(responseStatus.value(),responseStatus.getMessage(), null);
    }

    public static ObjectResponse getInstance(String code, String message) {
        return new ObjectResponse(code, message, null);
    }

    public static <T> ObjectResponse getInstance(String code, String message, T value) {
        return new ObjectResponse(code, message, value);
    }

}
