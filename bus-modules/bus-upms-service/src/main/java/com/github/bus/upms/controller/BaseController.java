package com.github.bus.upms.controller;

import com.github.bus.upms.model.oauth.UserDetail;
import com.github.bus.upms.utils.JSONUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Controller基类
 * Created by wangfan on 2018-02-22 上午 11:29.
 */
public class BaseController {

    /**
     * 获取当前登录的user
     */
    public UserDetail getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object object = authentication.getPrincipal();
            System.out.println(object.getClass().getName());
            System.out.println(UserDetail.class.getName());
            System.out.println(JSONUtil.toJsonString(object));
            if (object != null) {
                String userJson = JSONUtil.toJsonString(object);
                return JSONUtil.parseObject(userJson, UserDetail.class);
            }
        }
        return null;
    }

    /**
     * 获取当前登录的userId
     */
    public String getLoginUserId() {
        UserDetail loginUser = getLoginUser();
        return loginUser == null ? null : loginUser.getUserId();
    }

}
