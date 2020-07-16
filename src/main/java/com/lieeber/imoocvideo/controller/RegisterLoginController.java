package com.lieeber.imoocvideo.controller;

import com.lieeber.imoocvideo.pojo.Users;
import com.lieeber.imoocvideo.service.UserService;
import com.lieeber.imoocvideo.utils.IMoocJSONResult;
import com.lieeber.imoocvideo.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户注册和登录接口",tags = "注册和登录的controller")
@RequestMapping("user")
@RestController
public class RegisterLoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册",notes = "用户注册的接口")
    @PostMapping("/register")
    public IMoocJSONResult register(@RequestBody Users users) throws Exception {
        if (StringUtils.isBlank(users.getUsername())
                || StringUtils.isBlank(users.getPassword())) {
            return IMoocJSONResult.errorMsg("用户名和密码不能为空");
        }
        boolean userNameIsExit = userService.queryUserNameIsExit(users.getUsername());
        if (!userNameIsExit) {
            users.setNickname(users.getUsername());
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            userService.saveUser(users);
        }else{
            return IMoocJSONResult.errorMsg("用户名已经存在，请换一个再试。");
        }
        return IMoocJSONResult.ok(users);
    }
}
