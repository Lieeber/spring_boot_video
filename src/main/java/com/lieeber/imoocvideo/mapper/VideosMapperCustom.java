package com.lieeber.imoocvideo.mapper;

import com.lieeber.imoocvideo.pojo.Videos;
import com.lieeber.imoocvideo.pojo.vo.VideosVO;
import com.lieeber.imoocvideo.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VideosMapperCustom extends MyMapper<Videos> {

    public List<VideosVO> queryAllVideos();

}