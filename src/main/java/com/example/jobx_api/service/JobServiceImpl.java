package com.example.jobx_api.service;

import com.example.jobx_api.dao.JobDao;
import com.example.jobx_api.dto.JobDto;
import com.example.jobx_api.dto.SearchRequestDto;
import com.example.jobx_api.dto.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{

    private final JobDao jobDao;

    @Override
    public SearchResponseDto searchJobList(SearchRequestDto searchRequestDto) {

        List<JobDto> jobList = jobDao.selectJobList(searchRequestDto);

        return SearchResponseDto.builder()
                .jobList(jobList)
                .build();
    }

    @Override
    public SearchResponseDto searchJobInfo(SearchRequestDto searchRequestDto) {
        JobDto jobInfo = jobDao.selectJobInfo(searchRequestDto);

        return SearchResponseDto.builder()
                .jobInfo(jobInfo)
                .build();
    }
}
