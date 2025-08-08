package com.example.jobx_api.dao;

import com.example.jobx_api.dto.NewsDto;
import com.example.jobx_api.dto.SearchRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsDao {

    List<NewsDto> selectNews(SearchRequestDto searchRequestDto);

    Long selectNewsTotalCount(SearchRequestDto searchRequestDto);
}
