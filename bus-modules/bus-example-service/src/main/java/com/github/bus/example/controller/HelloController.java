/*
 * HelloController  1.0  2018-10-17
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.example.controller;

import com.github.bus.common.controller.BaseController;
import com.github.bus.common.response.ObjectResponse;
import com.github.bus.example.feign.FeignUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 演示实例
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-17 zy 初版
 */
@Slf4j
@RestController
@RequestMapping("/example")
public class HelloController extends BaseController {

    @Value("${server.port}")
    String port;

    @Value("${foo}")
    String foo;

    @Autowired
    private FeignUserService feignUserService;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ApiOperation(value = "演示hello world!")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        String message = foo + name + " ,i am from port:" + port;
        log.info(message);
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/testHystric", method = RequestMethod.GET)
    @ApiOperation(value = "演示断路器及链路跟踪", notes = "说明", response = ObjectResponse.class)
    public ObjectResponse testHystric(@RequestParam(value = "id") String id) {
        log.info("user get method is being called;");
        return feignUserService.get(id);
    }

}
