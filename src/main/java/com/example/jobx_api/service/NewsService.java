package com.example.jobx_api.service;


import com.example.jobx_api.dto.NewsDto;

import java.util.List;

public interface NewsService {

    List<NewsDto> selectNews();

}
