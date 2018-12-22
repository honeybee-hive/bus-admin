
package com.github.bus.upms.controller;

import com.github.bus.common.constant.ResponseStatus;
import com.github.bus.common.response.ObjectResponse;
import com.github.bus.upms.pojo.RoleMenuPojo;
import com.github.bus.upms.pojo.UserRolePojo;
import com.github.bus.upms.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色Controller
 *
 * @author zcq
 * @date 2018/10/23 20:58
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    //=================================权限菜单===============================

    @ApiOperation(value = "查询用户权限列表(分页)", notes = "", response = ObjectResponse.class)
    @RequestMapping(value = "/queryRoleAndMenu", method = RequestMethod.POST)
    public ObjectResponse queryRoleAndMenu(@RequestBody RoleMenuPojo roleMenuPojo) {
        List<RoleMenuPojo> list = sysRoleService.queryRoleAndMenu(roleMenuPojo);
        PageInfo<RoleMenuPojo> pager = new PageInfo<RoleMenuPojo>(list);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), pager);
    }

    @ApiOperation(value = "获取权限菜单信息", notes = "", response = ObjectResponse.class)
    @RequestMapping(value = "/getRoleAndMenu", method = RequestMethod.POST)
    public ObjectResponse getRoleAndMenu(String roleId) {
        RoleMenuPojo roleMenuPojo = sysRoleService.getRoleAndMenu(roleId);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), roleMenuPojo);
    }

    @ApiOperation(value = "保存权限及按钮信息", notes = "", response = ObjectResponse.class)
    @RequestMapping(value = "/saveRoleAndMenu", method = RequestMethod.POST)
    public ObjectResponse saveRoleAndMenu(@RequestBody RoleMenuPojo roleMenuPojo) {
        sysRoleService.saveRoleAndMenu(roleMenuPojo);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), roleMenuPojo);
    }

    @ApiOperation(value = "刪除权限及按钮信息", notes = "", response = ObjectResponse.class)
    @RequestMapping(value = "/deleteRoleAndMenu", method = RequestMethod.POST)
    public ObjectResponse deleteRoleAndMenu(String roleId) {
        sysRoleService.deleteRoleAndMenu(roleId);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage());
    }

    //=================================用户权限===================================

    @ApiOperation(value = "获取用户权限列表(分页)", notes = "", response = ObjectResponse.class)
    @RequestMapping(value = "/queryRoleList", method = RequestMethod.POST)
    public ObjectResponse queryRoleList(@RequestBody UserRolePojo rolePojo) {
        List<UserRolePojo> list = sysRoleService.queryUserRoleList(rolePojo);
        PageInfo<UserRolePojo> pager = new PageInfo<UserRolePojo>(list);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), pager);
    }

}
