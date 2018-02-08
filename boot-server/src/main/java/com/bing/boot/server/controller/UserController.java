package com.bing.boot.server.controller;

import com.bing.boot.server.request.ValidateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description:用户接口
 * Author: zhangfusheng
 * Date: 2017/11/1 上午11:31
 */
@RestController
@RequestMapping("/api/user")
@Api(value = "/api/user", description = "用户接口")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "普通登录", httpMethod = "POST", notes = "根据用户填写资料验证登录")
    public String login(@RequestParam String username) {
        logger.info("欢迎 {} 登录", username);
        return "login";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    @ApiOperation(value = "参数校验", httpMethod = "POST", notes = "参数校验")
    public Boolean validate(@Valid @RequestBody ValidateRequest request) {
        logger.info("校验成功，{}", request);
        return true;
    }
}
