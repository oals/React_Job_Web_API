package com.example.jobx_api.service;

import com.example.jobx_api.dao.NewsDao;
import com.example.jobx_api.dto.NewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao;

    @Override
    public List<NewsDto> selectNews() {
        return newsDao.selectNews();
    }


}
