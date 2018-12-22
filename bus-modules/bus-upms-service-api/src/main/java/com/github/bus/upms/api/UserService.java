package com.github.bus.upms.api;

import com.github.bus.common.response.ObjectResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户服务接口
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-18 zy 初版
 */
public interface UserService {
    @ResponseBody
    @RequestMapping(value = "/user/get", method = RequestMethod.GET)
    public ObjectResponse get(@RequestParam(value = "id") String id);
}
