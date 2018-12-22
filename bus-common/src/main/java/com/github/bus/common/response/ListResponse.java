/*
 * ResultList  1.0  2018-10-09
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.common.response;

import lombok.Data;

import java.util.List;

/**
 * 结果集
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-09 zy 初版
 */
@Data
public class ListResponse<T> extends BaseResponse {

    private List<T> data;

    public ListResponse(String code, String message, List<T> data) {
        super(code, message);
        this.data = data;
    }

    public static <T> ListResponse getInstance(String code, String message, List<T> data) {
        return new ListResponse(code, message, data);
    }
}
