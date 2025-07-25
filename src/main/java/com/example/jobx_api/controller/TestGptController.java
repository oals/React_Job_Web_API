package com.example.jobx_api.controller;

import com.example.jobx_api.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestGptController {


    private final GptService gptService;

    @GetMapping("/testcall")
    public String chat(@RequestParam String jobTestResult) {
        try {
            return gptService.getChatResponse(jobTestResult);
        } catch (Exception e) {
            return "에러 발생: " + e.getMessage();
        }
    }

    @GetMapping("/api/test/getdata")
    public String testApi() {
        try {
            return "안녕하세요";
        } catch (Exception e) {
            return "에러 발생: " + e.getMessage();
        }
    }

}
