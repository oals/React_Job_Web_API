package com.example.jobx_api.service;

import com.example.jobx_api.dto.JobQuestionRequestDto;

import java.io.IOException;

public interface LlamaService {

    String getChatResponse(JobQuestionRequestDto jobQuestionRequestDto) throws IOException;
}
