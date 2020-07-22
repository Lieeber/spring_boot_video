package com.lieeber.imoocvideo.service

import com.lieeber.imoocvideo.mapper.BgmMapper
import com.lieeber.imoocvideo.pojo.Bgm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class BgmServiceImpl : BgmService {

    @Autowired
    lateinit var bgmMapper: BgmMapper

    override fun queryBgmList(): ArrayList<Bgm> {
        val bgmList = bgmMapper.selectAll() as ArrayList
        return bgmList
    }

    override fun queryBgmById(bgmId: String): Bgm? {
        val bgm = bgmMapper.selectByPrimaryKey(bgmId)
        return bgm
    }
}