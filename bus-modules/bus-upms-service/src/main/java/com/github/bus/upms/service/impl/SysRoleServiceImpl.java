package com.github.bus.upms.service.impl;

import com.github.bus.upms.dao.SysRoleMapper;
import com.github.bus.upms.dao.SysRoleMenuMapper;
import com.github.bus.upms.dao.SysUserMapper;
import com.github.bus.upms.dao.SysUserRoleMapper;
import com.github.bus.upms.model.*;
import com.github.bus.upms.pojo.MenuButtonsPojo;
import com.github.bus.upms.pojo.RoleMenuPojo;
import com.github.bus.upms.pojo.UserRolePojo;
import com.github.bus.upms.service.SysRoleService;
import com.github.bus.upms.service.SysUserService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色Service
 *
 * @author zcq
 * @date 2018/11/13 16:17
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    //=================================权限菜单===================================

    @Override
    public List<RoleMenuPojo> queryRoleAndMenu(RoleMenuPojo roleMenuPojo) {
        PageHelper.startPage(roleMenuPojo.getPageNum(), roleMenuPojo.getPageSize());
        return sysRoleMenuMapper.queryRoleAndMenu(roleMenuPojo);
    }

    @Override
    public RoleMenuPojo getRoleAndMenu(String roleId) {
        RoleMenuPojo roleMenuPojo = new RoleMenuPojo();
        roleMenuPojo.setRoleId(roleId);
        List<RoleMenuPojo> list = sysRoleMenuMapper.queryRoleAndMenu(roleMenuPojo);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    @Override
    public void saveRoleAndMenu(RoleMenuPojo roleMenuPojo) {
        String roleId = roleMenuPojo.getRoleId();
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleMenuPojo, role);
        if (StringUtils.isNotBlank(roleId)) {
            // 删除
            sysRoleMenuMapper.deleteByRoleId(roleId);
            // 修改
            sysRoleMapper.updateByPrimaryKeySelective(role);
        } else {
            // 新增
            sysRoleMapper.insert(role);
            roleId = role.getRoleId();
        }
        // 添加
        List<SysRoleMenu> roleMenuList = roleMenuPojo.getRoleMenuList();
        if (CollectionUtils.isNotEmpty(roleMenuList) && sysRoleMapper.selectByPrimaryKey(roleId) != null) {
            for (SysRoleMenu roleMenu : roleMenuList) {
                roleMenu.setRoleId(roleId);
                sysRoleMenuMapper.insert(roleMenu);
            }
        }
    }

    @Override
    public void deleteRoleAndMenu(String roleId) {
        // 刪除菜单
        sysRoleMenuMapper.deleteByRoleId(roleId);
        // 删除权限
        sysRoleMapper.deleteByPrimaryKey(roleId);
    }

    //=================================用户权限===================================

    @Override
    public List<UserRolePojo> queryUserRoleList(UserRolePojo userRolePojo) {
        PageHelper.startPage(userRolePojo.getPageNum(), userRolePojo.getPageSize());
        return sysUserRoleMapper.queryUserRoleList(userRolePojo);
    }

}
