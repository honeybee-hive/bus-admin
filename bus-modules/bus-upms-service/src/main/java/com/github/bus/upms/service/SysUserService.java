package com.github.bus.upms.service;

import com.github.bus.upms.model.SysUser;
import com.github.bus.upms.pojo.UserPojo;

import java.util.List;

/**
 * 用户Service
 *
 * @author zcq
 * @date 2018/11/13 16:16
 */
public interface SysUserService {

    int deleteUser(String userId);

    String addUser(UserPojo userPojo);

    SysUser getUserByUserId(String userId);

    void updateUser(UserPojo userPojo);

    SysUser getUserByUserCode(String userCode);

    List<UserPojo> queryUser(UserPojo userPojo);

}
