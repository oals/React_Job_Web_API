package com.example.jobx_api.dao;

import com.example.jobx_api.dto.JobDto;
import com.example.jobx_api.dto.JobRequestDto;
import com.example.jobx_api.dto.SearchRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobDao {

    List<JobDto> selectJobList(SearchRequestDto searchRequestDto);

    Long selectJobListTotalCount(SearchRequestDto searchRequestDto);

    JobDto selectJobInfo(SearchRequestDto searchRequestDto);

    List<JobDto> selectPopularJobList();

    void insertBookmarks(JobRequestDto jobRequestDto);

    void deleteBookmarks(JobRequestDto jobRequestDto);

}
