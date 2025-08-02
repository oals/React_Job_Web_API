package com.example.jobx_api.controller;

import com.example.jobx_api.dto.NewsDto;
import com.example.jobx_api.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;


    @GetMapping("/api/news/search")
    public List<NewsDto> searchNews(){

        return newsService.selectNews();
    }

}
