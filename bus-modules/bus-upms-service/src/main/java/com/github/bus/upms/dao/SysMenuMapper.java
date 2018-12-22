package com.github.bus.upms.dao;

import com.github.bus.upms.model.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<String> queryMenuByUserId(String userId);
}
