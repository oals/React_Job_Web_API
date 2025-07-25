package com.example.jobx_api.service;

import java.io.IOException;

public interface GptService {

    String getChatResponse(String userMessage) throws IOException;
}
