
package com.github.bus.upms.service;


import com.github.bus.upms.pojo.MenuButtonsPojo;

import java.util.List;

/**
 * 菜单service
 *
 * @author zcq
 * @date 2018/10/21 20:45
 */
public interface SysMenuService {

    /**
     * 查询权限菜档
     *
     * @param menuPojo
     * @return java.util.List<com.itbang.pojo.system.MenuPojo>
     * @author zcq
     * @date 2018/11/11 下午5:02
     */
    List<MenuButtonsPojo> queryMenuAndButtons(MenuButtonsPojo menuPojo);

    MenuButtonsPojo getMenuAndButtons(String menuId);

    void saveMenuAndButtons(MenuButtonsPojo menuButtonsPojo);

    void deleteMenuAndButtons(String menuId);

}
