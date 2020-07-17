package com.lieeber.imoocvideo.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController:BasicController() {

    @PostMapping("/upload_avatar")
    fun uploadAvatar(){

    }
}