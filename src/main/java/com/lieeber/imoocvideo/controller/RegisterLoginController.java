package com.lieeber.imoocvideo.controller;

import com.lieeber.imoocvideo.pojo.Users;
import com.lieeber.imoocvideo.pojo.UsersVO;
import com.lieeber.imoocvideo.service.UserService;
import com.lieeber.imoocvideo.utils.IMoocJSONResult;
import com.lieeber.imoocvideo.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(value = "用户注册和登录接口", tags = "注册和登录的controller")
@RequestMapping("user")
@RestController
public class RegisterLoginController extends BasicController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "用户注册的接口")
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
            users.setFansCounts(0);
            users.setFollowCounts(0);
            users.setReceiveLikeCounts(0);
            userService.saveUser(users);
        } else {
            return IMoocJSONResult.errorMsg("用户名已经存在，请换一个再试。");
        }
        users.setPassword("");
        UsersVO usersVO = saveToRedis(users);
        return IMoocJSONResult.ok(usersVO);
    }


    @ApiOperation(value = "用户登录", notes = "用户登录的接口")
    @PostMapping("/login")
    public IMoocJSONResult login(@RequestBody Users users) throws Exception {
        if (StringUtils.isBlank(users.getUsername())
                || StringUtils.isBlank(users.getPassword())) {
            return IMoocJSONResult.errorMsg("用户名和密码不能为空");
        }
        Users responseUser = userService.queryUserForLogin(users.getUsername(), MD5Utils.getMD5Str(users.getPassword()));
        if (responseUser != null) {
            responseUser.setPassword("");
            UsersVO usersVO = saveToRedis(responseUser);
            return IMoocJSONResult.ok(usersVO);
        } else {
            return IMoocJSONResult.errorMsg("没有找到该用户，请确认用户名或者密码是否有误。");
        }
    }


    @ApiOperation(value = "用户注销", notes = "用户注销的接口")
    @ApiImplicitParam(name = "userToken", value = "用户Token", required = true,
            dataType = "String", paramType = "query")
    @PostMapping("/logout")
    public IMoocJSONResult logout(@RequestParam String userToken) throws Exception {
        String userId = getRedis().get(USER_REDIS_SESSION + ":" + userToken);
        getRedis().del((USER_REDIS_SESSION + ":" + userToken));
        return IMoocJSONResult.ok("退出登录成功");
    }

    @NotNull
    private UsersVO saveToRedis(@RequestBody Users users) {
        String uuid = UUID.randomUUID().toString();
        getRedis().set(USER_REDIS_SESSION + ":" + uuid, users.getId());
        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(users, usersVO);
        usersVO.setUserToken(uuid);
        return usersVO;
    }
}
