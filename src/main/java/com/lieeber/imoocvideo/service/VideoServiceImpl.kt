package com.lieeber.imoocvideo.service

import com.lieeber.imoocvideo.mapper.VideosMapper
import com.lieeber.imoocvideo.pojo.Videos
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VideoServiceImpl:VideoService {

    @Autowired
    lateinit var videosMapper: VideosMapper
    override fun saveVideo(video: Videos) {
        videosMapper.insertSelective(video)
    }
}