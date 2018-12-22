package com.github.bus.example.feign;

import com.github.bus.example.feign.fallback.FeignUserServiceFallback;
import com.github.bus.upms.api.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户服务客户端
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-31 zy 初版
 */
@FeignClient(name = "bus-upms-service", fallback = FeignUserServiceFallback.class)
public interface FeignUserService extends UserService {
}
