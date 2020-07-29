package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.configuration.MyConfiguration
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


    @Autowired
    lateinit  var configuration:MyConfiguration
    @GetMapping("/get")
    fun hello(): String {
        return configuration.toString()
    }
}