package com.example.jobx_api.service;

import com.example.jobx_api.dto.SearchRequestDto;
import com.example.jobx_api.dto.SearchResponseDto;

public interface JobService {

    SearchResponseDto searchJobList(SearchRequestDto searchRequestDto);

    SearchResponseDto searchJobInfo(SearchRequestDto searchRequestDto);

}
