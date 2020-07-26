package com.lieeber.imoocvideo.service

import com.lieeber.imoocvideo.mapper.VideosMapper
import com.lieeber.imoocvideo.pojo.Videos
import org.n3r.idworker.Sid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class VideoServiceImpl:VideoService {

    @Autowired
    lateinit var  videosMapper: VideosMapper

    @Autowired
    lateinit var  sid: Sid

    @Transactional(propagation = Propagation.REQUIRED)
    override fun saveVideo(videos: Videos) {
        val nextShort = sid.nextShort()
        videos.id = nextShort
        videosMapper.insertSelective(videos)
    }
}