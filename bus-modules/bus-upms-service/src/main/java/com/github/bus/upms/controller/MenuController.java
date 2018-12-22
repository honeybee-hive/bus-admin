
package com.github.bus.upms.controller;

import com.github.bus.common.constant.ResponseStatus;
import com.github.bus.common.response.ObjectResponse;
import com.github.bus.upms.pojo.MenuButtonsPojo;
import com.github.bus.upms.service.SysMenuService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebResult;
import java.util.List;

/**
 * 菜单Controller
 *
 * @author zcq
 * @date 2018/10/23 20:58
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    // @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "header")
    @ApiOperation(value = "获取权限菜单列表(分页)", notes = "", response = WebResult.class)
    @RequestMapping(value = "/queryMenuButtons", method = RequestMethod.POST)
    public ObjectResponse queryMenuButtons(@RequestBody MenuButtonsPojo menuButtonsPojo) {
        List<MenuButtonsPojo> list = sysMenuService.queryMenuAndButtons(menuButtonsPojo);
        PageInfo<MenuButtonsPojo> pager = new PageInfo<MenuButtonsPojo>(list);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), pager);
    }

    @ApiOperation(value = "保存权限菜单", notes = "", response = WebResult.class)
    @RequestMapping(value = "/saveMenuAndButtons", method = RequestMethod.POST)
    public ObjectResponse saveMenuAndButtons(@RequestBody MenuButtonsPojo menuButtonsPojo) {
        sysMenuService.saveMenuAndButtons(menuButtonsPojo);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage());
    }

    @ApiOperation(value = "主键获取权限菜单信息", notes = "注释说明", response = ObjectResponse.class)
    @RequestMapping(value = "/getMenuAndButtons", method = RequestMethod.POST)
    public ObjectResponse getMenuAndButtons(String menuId) {
        MenuButtonsPojo menuButtons = sysMenuService.getMenuAndButtons(menuId);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), menuButtons);
    }

    @ApiOperation(value = "删除菜单及按钮信息", notes = "", response = ObjectResponse.class)
    @RequestMapping(value = "/deleteMenuAndButtons", method = RequestMethod.POST)
    public ObjectResponse deleteMenuAndButtons(@RequestParam String menuId) {
        sysMenuService.deleteMenuAndButtons(menuId);
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), true);
    }

}
