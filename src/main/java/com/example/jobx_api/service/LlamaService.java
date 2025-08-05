package com.example.jobx_api.service;

import com.example.jobx_api.dto.JobQuestionRequestDto;

import java.io.IOException;

public interface LlamaService {

    String getRecommendJob(JobQuestionRequestDto jobQuestionRequestDto) throws IOException;


}
