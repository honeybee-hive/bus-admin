package com.github.bus.upms.dao;

import com.github.bus.upms.model.SysUserRole;
import com.github.bus.upms.pojo.UserRolePojo;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    /**
     * 检索权限信息
     *
     * @param userRolePojo
     * @return java.util.List<com.itbang.pojo.system.RolePojo>
     * @author zcq
     * @date 2018/10/31 21:43
     */
    List<UserRolePojo> queryUserRoleList(UserRolePojo userRolePojo);

    int deleteByUserId(String userId);
}
