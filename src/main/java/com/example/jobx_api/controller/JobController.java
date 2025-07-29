package com.example.jobx_api.controller;

import com.example.jobx_api.dto.SearchRequestDto;
import com.example.jobx_api.dto.SearchResponseDto;
import com.example.jobx_api.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/api/job/search/list")
    public ResponseEntity<SearchResponseDto> searchJobList(SearchRequestDto searchRequestDto) {
       SearchResponseDto searchResponseDto = jobService.searchJobList(searchRequestDto);
       return ResponseEntity.ok(searchResponseDto);
    }

    @GetMapping("/api/job/search/info")
    public ResponseEntity<SearchResponseDto> searchJobInfo(SearchRequestDto searchRequestDto) {
        SearchResponseDto searchResponseDto = jobService.searchJobInfo(searchRequestDto);
        return ResponseEntity.ok(searchResponseDto);
    }
}
