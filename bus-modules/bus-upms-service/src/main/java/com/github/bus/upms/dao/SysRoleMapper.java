package com.github.bus.upms.dao;

import com.github.bus.upms.model.SysRole;
import com.github.bus.upms.pojo.RolePojo;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<RolePojo> queryRole(RolePojo rolePojo);
}
