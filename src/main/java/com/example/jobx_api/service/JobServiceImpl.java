package com.example.jobx_api.service;

import com.example.jobx_api.dao.JobDao;
import com.example.jobx_api.dto.JobDto;
import com.example.jobx_api.dto.JobRequestDto;
import com.example.jobx_api.dto.SearchRequestDto;
import com.example.jobx_api.dto.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{

    private final JobDao jobDao;

    @Override
    public SearchResponseDto searchJobList(SearchRequestDto searchRequestDto) {

        List<JobDto> jobList = new ArrayList<>();

        Long totalCount = jobDao.selectJobListTotalCount(searchRequestDto);

        if (totalCount != 0L) {
            searchRequestDto.setOffset(searchRequestDto.getOffset());
            jobList = jobDao.selectJobList(searchRequestDto);
        }

        return SearchResponseDto.builder()
                .jobList(jobList)
                .totalCount(totalCount)
                .build();
    }

    @Override
    public SearchResponseDto searchJobInfo(SearchRequestDto searchRequestDto) {
        JobDto jobInfo = jobDao.selectJobInfo(searchRequestDto);

        return SearchResponseDto.builder()
                .jobInfo(jobInfo)
                .build();
    }

    @Override
    public SearchResponseDto searchPopularJob() {


        List<JobDto> popularJobList = jobDao.selectPopularJobList();

        return SearchResponseDto.builder()
                .jobList(popularJobList)
                .build();
    }

    @Override
    public SearchResponseDto searchBookmarksJob(SearchRequestDto searchRequestDto) {
        List<JobDto> popularJobList = jobDao.selectJobList(searchRequestDto);

        return SearchResponseDto.builder()
                .jobList(popularJobList)
                .build();
    }

    @Override
    public boolean saveBookmarks(JobRequestDto jobRequestDto) {

        try {
            if (jobRequestDto.isBookmark()) {
                jobDao.deleteBookmarks(jobRequestDto);
            } else {
                jobDao.insertBookmarks(jobRequestDto);
            }
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
