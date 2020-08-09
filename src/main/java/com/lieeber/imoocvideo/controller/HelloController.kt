package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.configuration.FirstConfiguration
import com.lieeber.imoocvideo.configuration.MyConfiguration
import com.lieeber.imoocvideo.pojo.Users
import com.lieeber.imoocvideo.service.UserService
import com.lieeber.imoocvideo.utils.RedisOperator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.sql.SQLIntegrityConstraintViolationException
import java.util.*
import kotlin.collections.HashMap

@RestController
@RequestMapping("hello")
class HelloController : BasicController() {
    @Autowired
    var userService: UserService? = null


    @Autowired
    lateinit  var configuration:MyConfiguration

    @Autowired
    lateinit  var firstConfiguration: FirstConfiguration


    @GetMapping("get")
    fun hello(): String {
        return configuration.toString()
    }

    @PostMapping("/post")
    fun postHello(@RequestBody name:HashMap<String,String>): String {
        println(name.toString() )
        return name .toString()

    }
}
