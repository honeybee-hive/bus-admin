
package com.github.bus.upms.service.impl;

import com.github.bus.upms.dao.SysMenuButtonsMapper;
import com.github.bus.upms.dao.SysMenuMapper;
import com.github.bus.upms.model.SysMenu;
import com.github.bus.upms.model.SysMenuButtons;
import com.github.bus.upms.pojo.MenuButtonsPojo;
import com.github.bus.upms.service.SysMenuService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 菜单service
 *
 * @author zcq
 * @date 2018/10/21 20:45
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysMenuButtonsMapper sysMenuButtonsMapper;

    @Override
    public List<MenuButtonsPojo> queryMenuAndButtons(MenuButtonsPojo menuPojo) {
        PageHelper.startPage(menuPojo.getPageNum(), menuPojo.getPageSize());
        return sysMenuButtonsMapper.queryMenuButtons(menuPojo);
    }

    @Override
    public MenuButtonsPojo getMenuAndButtons(String menuId) {
        MenuButtonsPojo menuPojo = new MenuButtonsPojo();
        menuPojo.setMenuId(menuId);
        List<MenuButtonsPojo> list = sysMenuButtonsMapper.queryMenuButtons(menuPojo);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
    public void saveMenuAndButtons(MenuButtonsPojo menuButtonsPojo) {
        String menuId = menuButtonsPojo.getMenuId();
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuButtonsPojo, menu);
        if (StringUtils.isNotBlank(menuId)) {
            // 删除
            sysMenuButtonsMapper.deleteByMenuId(menuId);
            // 修改
            sysMenuMapper.updateByPrimaryKeySelective(menu);
        } else {
            // 新增
            sysMenuMapper.insert(menu);
            menuId = menu.getMenuId();
        }
        // 添加
        List<SysMenuButtons> buttonList = menuButtonsPojo.getButtonList();
        if (CollectionUtils.isNotEmpty(buttonList) && sysMenuMapper.selectByPrimaryKey(menuId) != null) {
            for (SysMenuButtons button : buttonList) {
                sysMenuButtonsMapper.insert(button);
            }
        }
    }

    @Override
    public void deleteMenuAndButtons(String menuId) {
        // 刪除按钮
        sysMenuButtonsMapper.deleteByMenuId(menuId);
        // 刪除菜单
        sysMenuMapper.deleteByPrimaryKey(menuId);
    }

}
