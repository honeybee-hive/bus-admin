package com.github.bus.upms.service;

import com.github.bus.upms.pojo.RoleMenuPojo;
import com.github.bus.upms.pojo.UserRolePojo;

import java.util.List;

/**
 * 角色Service
 *
 * @author zcq
 * @date 2018/11/13 16:16
 */
public interface SysRoleService {

    //=================================权限菜单===================================

    List<RoleMenuPojo> queryRoleAndMenu(RoleMenuPojo roleMenuPojo);

    RoleMenuPojo getRoleAndMenu(String roleId);

    void saveRoleAndMenu(RoleMenuPojo roleMenuPojo);

    void deleteRoleAndMenu(String roleId);

    //=================================用户权限===================================

    /**
     * 查询用户角色
     *
     * @param userRolePojo
     * @return java.util.List<com.itbang.pojo.system.UserRolePojo>
     * @author zcq
     * @date 2018/10/21 20:46
     */
    List<UserRolePojo> queryUserRoleList(UserRolePojo userRolePojo);

}
