/*
 * TestEndpoints  1.0  2018-10-24
 *
 * 沈阳成林健康科技有限公司
 *
 */
package com.github.bus.example.controller;

import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试资源认证
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-10-24 zy 初版
 */
@Slf4j
@RestController
public class TestEndpoints {

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }
}
