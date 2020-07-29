package com.lieeber.imoocvideo.service

import com.lieeber.imoocvideo.pojo.SearchRecords
import com.lieeber.imoocvideo.pojo.Videos
import com.lieeber.imoocvideo.utils.PagedResult

interface VideoService {
    fun saveVideo(video: Videos)
    fun getAllVideos(videoDesc: String? = null, page: Int, pageSize: Int): PagedResult

    fun getHotWords(): ArrayList<String>


}