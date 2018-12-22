package com.github.bus.upms.controller;

import com.github.bus.common.constant.ResponseStatus;
import com.github.bus.common.response.ObjectResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Api(value = "登录注册相关的接口", tags = "main-controller")
@RestController
public class MainController extends BaseController {

    @ApiOperation(value = "获取个人信息")
    @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "header")
    @GetMapping("/userInfo")
    public ObjectResponse userInfo() {
        return ObjectResponse.getInstance(ResponseStatus.OK.value(), ResponseStatus.OK.getMessage(), getLoginUser());
    }
}
