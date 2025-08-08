package com.example.jobx_api.service;


import com.example.jobx_api.dto.NewsDto;
import com.example.jobx_api.dto.SearchRequestDto;
import com.example.jobx_api.dto.SearchResponseDto;

import java.util.List;

public interface NewsService {

    SearchResponseDto searchNews(SearchRequestDto searchRequestDto);

}
