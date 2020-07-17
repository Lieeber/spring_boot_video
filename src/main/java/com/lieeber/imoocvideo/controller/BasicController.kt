package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.utils.RedisOperator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

open class BasicController {

    @Autowired
    var redis: RedisOperator? = null

    companion object{
        const val USER_REDIS_SESSION = "user-redis-session"
    }
}