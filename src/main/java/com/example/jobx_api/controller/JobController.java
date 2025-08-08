package com.example.jobx_api.controller;

import com.example.jobx_api.dto.JobRequestDto;
import com.example.jobx_api.dto.SearchRequestDto;
import com.example.jobx_api.dto.SearchResponseDto;
import com.example.jobx_api.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @GetMapping("/api/job/search/popular")
    public ResponseEntity<SearchResponseDto> searchPopularJob() {
        SearchResponseDto searchResponseDto = jobService.searchPopularJob();
        return ResponseEntity.ok(searchResponseDto);
    }

    @GetMapping("/api/job/bookmark/select")
    public ResponseEntity<SearchResponseDto> selectBookmarks(SearchRequestDto searchRequestDto) {
        SearchResponseDto searchResponseDto = jobService.searchBookmarksJob(searchRequestDto);
        return ResponseEntity.ok(searchResponseDto);
    }

    @PostMapping("/api/job/bookmark/insert")
    public ResponseEntity<String> insertBookmarks(@RequestBody JobRequestDto jobRequestDto){

        boolean result = jobService.saveBookmarks(jobRequestDto);
        if (result) {
            return ResponseEntity.ok("북마크 등록되었습니다");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("북마크 등록 실패");
        }

    }
}
