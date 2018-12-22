package com.github.bus.upms.dao;

import com.github.bus.upms.model.SysUser;
import com.github.bus.upms.model.oauth.UserDetail;
import com.github.bus.upms.pojo.UserPojo;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser getUserByUserCode(String userCode);

    UserDetail getUserDetailByUserCode(String userCode);

    List<UserPojo> queryUser(UserPojo userPojo);
}
