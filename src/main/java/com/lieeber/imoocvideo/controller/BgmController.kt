package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.Constants
import com.lieeber.imoocvideo.pojo.Bgm
import com.lieeber.imoocvideo.service.BgmService
import com.lieeber.imoocvideo.utils.IMoocJSONResult
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

    @GetMapping("/list")
    fun bgmList(): IMoocJSONResult {
        val queryBgmList = bgmService.queryBgmList()
//        if (queryBgmList.isEmpty()) {//如果数据库的数据是空，就获取文件夹下面的文件，从而知道数据
//            val dir = File(Constants.rootPath + File.separator + "bgm")
//            if (dir.isDirectory) {
//                val listFiles = dir.listFiles()
//                if (!listFiles.isNullOrEmpty()) {
//                    for (file in listFiles) {
//                        val name = file.name
//                        val bgm = Bgm()
//                        bgm.name = name
//                        bgm.id = "10001"
//                        bgm.author = "林俊杰"
//                        bgm.path = File.separator + "bgm" + File.separator + name
//                        queryBgmList.add(bgm)
//                    }
//                }
//            }
//        }
        return IMoocJSONResult.ok(queryBgmList)
    }
}