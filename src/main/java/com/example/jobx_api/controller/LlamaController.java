package com.example.jobx_api.controller;

import com.example.jobx_api.dto.JobQuestionRequestDto;
import com.example.jobx_api.dto.MemberDto;
import com.example.jobx_api.service.JobService;
import com.example.jobx_api.service.LlamaService;
import com.example.jobx_api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LlamaController {

    private final LlamaService llamaService;

    @PostMapping("/api/test/insert/question")
    public String chat(@RequestBody JobQuestionRequestDto jobQuestionRequestDto) {
        try {

            return llamaService.getRecommendJob(jobQuestionRequestDto);
        } catch (Exception e) {
            return "에러 발생: " + e.getMessage();
        }
    }

}
