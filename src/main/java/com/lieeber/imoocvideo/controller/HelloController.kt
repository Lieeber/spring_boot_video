package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.pojo.Users
import com.lieeber.imoocvideo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hello")
class HelloController {
    @Autowired
    var userService: UserService? = null

    @GetMapping("/get")
    fun hello(): String {
        var user = Users()
        user.username = "fdfd"
        user.password = "fdfdfd"
        val nameIsExit = userService?.queryUserNameIsExit(user.username)
        return "访问成功了:$nameIsExit"
    }
}