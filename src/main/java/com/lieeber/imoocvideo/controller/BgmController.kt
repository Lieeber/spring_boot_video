package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.Constants
import com.lieeber.imoocvideo.pojo.Bgm
import com.lieeber.imoocvideo.service.BgmService
import com.lieeber.imoocvideo.utils.JsonUtils
import com.lieeber.imoocvideo.utils.UnifyResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File


@RestController
@RequestMapping("bgm")
class BgmController : BasicController() {

    @Autowired
    lateinit var bgmService: BgmService

    @GetMapping("list")
    fun bgmList(): String {
        val queryBgmList = bgmService.queryBgmList()
        val objectToJson = JsonUtils.objectToJson(UnifyResponse.ok(queryBgmList))
        return objectToJson

    }
}
