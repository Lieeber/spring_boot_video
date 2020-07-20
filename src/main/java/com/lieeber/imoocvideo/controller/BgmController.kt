package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.service.BgmService
import com.lieeber.imoocvideo.utils.IMoocJSONResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("bgm")
class BgmController {

    @Autowired
    lateinit var bgmService: BgmService

    @GetMapping("/list")
    fun bgmList():IMoocJSONResult {
        val queryBgmList = bgmService.queryBgmList()
        return IMoocJSONResult.ok(queryBgmList)
    }
}