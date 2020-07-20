package com.lieeber.imoocvideo.service

import com.lieeber.imoocvideo.pojo.Bgm
import org.springframework.stereotype.Service

interface BgmService {
    fun queryBgmList(): List<Bgm>

}