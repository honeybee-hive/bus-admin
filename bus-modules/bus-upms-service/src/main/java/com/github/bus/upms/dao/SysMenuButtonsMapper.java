package com.github.bus.upms.dao;

import com.github.bus.upms.model.SysMenuButtons;
import com.github.bus.upms.pojo.MenuButtonsPojo;

import java.util.List;

public interface SysMenuButtonsMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMenuButtons record);

    int insertSelective(SysMenuButtons record);

    SysMenuButtons selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenuButtons record);

    int updateByPrimaryKey(SysMenuButtons record);

    int deleteByMenuId(String menuId);

    List<MenuButtonsPojo> queryMenuButtons(MenuButtonsPojo menuPojo);
}
