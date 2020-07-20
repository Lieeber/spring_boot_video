package com.lieeber.imoocvideo.service

import com.lieeber.imoocvideo.mapper.BgmMapper
import com.lieeber.imoocvideo.pojo.Bgm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class BgmServiceImpl:BgmService {

    @Autowired
    lateinit var bgmMapper:BgmMapper

    override fun queryBgmList(): List<Bgm> {
        val bgmList = bgmMapper.selectAll()
        return bgmList
    }
}