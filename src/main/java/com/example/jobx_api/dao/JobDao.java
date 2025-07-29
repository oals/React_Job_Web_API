package com.example.jobx_api.dao;

import com.example.jobx_api.dto.JobDto;
import com.example.jobx_api.dto.SearchRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobDao {

    List<JobDto> selectJobList(SearchRequestDto searchRequestDto);

    JobDto selectJobInfo(SearchRequestDto searchRequestDto);

}
