package com.lieeber.imoocvideo.mapper;

import com.lieeber.imoocvideo.pojo.SearchRecords;
import com.lieeber.imoocvideo.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SearchRecordsMapper extends MyMapper<SearchRecords> {
    ArrayList<String> getHotWords();
}