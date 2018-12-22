/*
 * UserServiceFallback  1.0  2018-10-18
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.example.feign.fallback;

import com.github.bus.common.constant.ResponseStatus;
import com.github.bus.common.response.ObjectResponse;
import com.github.bus.example.feign.FeignUserService;
import org.springframework.stereotype.Component;

/**
 * 用户服务断路器
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-18 zy 初版
 */
@Component
public class FeignUserServiceFallback implements FeignUserService {
    @Override
    public ObjectResponse get(String id) {
        return ObjectResponse.getInstance(ResponseStatus.NO_LOSE_SERVICE.value(), ResponseStatus.NO_LOSE_SERVICE.getMessage());
    }
}
