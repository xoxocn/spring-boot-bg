package com.xoxo.bgms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.xoxo.bgms.controller
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-01-08 14:40
 */
@RestController
public class TestController {

    @GetMapping(value = "hello")
    public String hello(@RequestParam(value = "who") String whoStr){
        return "hello："+ whoStr;
    }

}
