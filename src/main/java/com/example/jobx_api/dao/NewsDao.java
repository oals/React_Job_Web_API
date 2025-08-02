package com.example.jobx_api.dao;

import com.example.jobx_api.dto.NewsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsDao {

    List<NewsDto> selectNews();
}
