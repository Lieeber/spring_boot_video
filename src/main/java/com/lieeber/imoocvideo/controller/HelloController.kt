package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.pojo.Users
import com.lieeber.imoocvideo.service.UserService
import com.lieeber.imoocvideo.utils.RedisOperator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("hello")
class HelloController : BasicController() {
    @Autowired
    var userService: UserService? = null

    @GetMapping("/get")
    fun hello(): String {
        var user = Users()
        user.username = "fdfd"
        user.password = "fdfdfd"
        val nameIsExit = userService?.queryUserNameIsExit(user.username)
        var token = UUID.randomUUID().toString()
        redis!!.set(USER_REDIS_SESSION + ":" + "ffff", token)
        return "访问成功了:$nameIsExit:${  redis!!.get(USER_REDIS_SESSION + ":" + "ffff")}"
    }
}