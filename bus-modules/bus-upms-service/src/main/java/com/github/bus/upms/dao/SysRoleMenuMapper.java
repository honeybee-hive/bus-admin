package com.github.bus.upms.dao;

import com.github.bus.upms.model.SysRoleMenu;
import com.github.bus.upms.pojo.RoleMenuPojo;

import java.util.List;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    List<RoleMenuPojo> queryRoleAndMenu(RoleMenuPojo roleMenuPojo);

    int deleteByRoleId(String roleId);

}
