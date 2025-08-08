package com.example.jobx_api.service;

import com.example.jobx_api.dao.NewsDao;
import com.example.jobx_api.dto.NewsDto;
import com.example.jobx_api.dto.SearchRequestDto;
import com.example.jobx_api.dto.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao;

    @Override
    public SearchResponseDto searchNews(SearchRequestDto searchRequestDto) {

        List<NewsDto> newsDtoList = new ArrayList<>();

        Long totalCount = newsDao.selectNewsTotalCount(searchRequestDto);
        if (totalCount != 0L) {
            searchRequestDto.setOffset(searchRequestDto.getOffset());
            newsDtoList = newsDao.selectNews(searchRequestDto);;
        }

        return SearchResponseDto.builder()
                .newsList(newsDtoList)
                .totalCount(totalCount)
                .build();
    }


}
