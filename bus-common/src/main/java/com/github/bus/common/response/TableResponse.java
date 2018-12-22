/*
 * ResultPager  1.0  2018-10-09
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.common.response;

import com.github.bus.common.model.Pager;
import lombok.Data;

/**
 * 结果分页
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-09 zy 初版
 */
@Data
public class TableResponse extends BaseResponse {

    private Pager pager;

    public TableResponse(String code, String message, Pager pager) {
        super(code, message);
        this.pager = pager;
    }

    public static TableResponse getInstance(String code, String message, Pager pager) {
        return new TableResponse(code, message, pager);
    }
}
