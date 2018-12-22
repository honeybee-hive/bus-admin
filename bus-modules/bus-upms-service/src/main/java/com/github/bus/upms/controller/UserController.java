/*
 * UserController  1.0  2018-10-08
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.upms.controller;

import com.github.bus.common.constant.ResponseStatus;
import com.github.bus.common.controller.BaseController;
import com.github.bus.common.response.ObjectResponse;
import com.github.bus.upms.model.SysUser;
import com.github.bus.upms.pojo.UserPojo;
import com.github.bus.upms.service.SysUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 *
 * @author zcq
 * @date 2018/11/10 14:21
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "主鍵获取用户信息接口", notes = "注释说明", response = ObjectResponse.class)
    @RequestMapping(value = "/getUserByUserId", method = RequestMethod.POST)
    public ObjectResponse getUserByUserId(String userId) {
        SysUser sysUser = sysUserService.getUserByUserId(userId);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), sysUser);
    }

    @ApiOperation(value = "工号获取用户信息接口", notes = "注释说明", response = ObjectResponse.class)
    @RequestMapping(value = "/getUserByUserCode", method = RequestMethod.POST)
    public ObjectResponse getUserByUserCode(String userCode) {
        SysUser sysUser = sysUserService.getUserByUserCode(userCode);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), sysUser);
    }

    @ApiOperation(value = "查询所有用户", notes = "")
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    public ObjectResponse queryUser(@RequestBody UserPojo userPojo) {
        List<UserPojo> list = sysUserService.queryUser(userPojo);
        PageInfo<UserPojo> pager = new PageInfo<UserPojo>(list);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), pager);
    }

    @ApiOperation(value = "删除用户", notes = "")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ObjectResponse deleteUser(@RequestParam String userId) {
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), sysUserService.deleteUser(userId));
    }

    @ApiOperation(value = "添加用户", notes = "")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ObjectResponse addUser(@RequestBody UserPojo userPojo) {
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), sysUserService.addUser(userPojo));
    }

    @ApiOperation(value = "修改用户", notes = "")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ObjectResponse updateUser(@RequestBody UserPojo userPojo) {
        sysUserService.updateUser(userPojo);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage());
    }

}
