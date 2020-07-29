package com.lieeber.imoocvideo.service

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.lieeber.imoocvideo.mapper.SearchRecordsMapper
import com.lieeber.imoocvideo.mapper.VideosMapper
import com.lieeber.imoocvideo.mapper.VideosMapperCustom
import com.lieeber.imoocvideo.pojo.SearchRecords
import com.lieeber.imoocvideo.pojo.Videos
import com.lieeber.imoocvideo.pojo.vo.VideosVO
import com.lieeber.imoocvideo.utils.PagedResult
import org.n3r.idworker.Sid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import tk.mybatis.mapper.entity.Example

@Service
class VideoServiceImpl : VideoService {

    @Autowired
    lateinit var videosMapper: VideosMapper

    @Autowired
    lateinit var videosMapperCustom: VideosMapperCustom


    @Autowired
    lateinit var searchRecordsMapper: SearchRecordsMapper


    @Autowired
    lateinit var sid: Sid

    @Transactional(propagation = Propagation.REQUIRED)
    override fun saveVideo(videos: Videos) {
        val nextShort = sid.nextShort()
        videos.id = nextShort
        videosMapper.insertSelective(videos)
    }

    override fun getAllVideos(videoDesc: String?, page: Int, pageSize: Int): PagedResult {
        PageHelper.startPage<VideosVO>(page, pageSize)
        val list = videosMapperCustom.queryAllVideos(videoDesc)
        val pageList = PageInfo<VideosVO>(list)
        val pageResult = PagedResult()
        pageResult.page = page
        pageResult.total = pageList.pages
        pageResult.rows = list
        pageResult.records = pageList.total
        return pageResult
    }

    override fun getHotWords(): ArrayList<String> {
        val hotWords = searchRecordsMapper.getHotWords()
        return hotWords
    }
}