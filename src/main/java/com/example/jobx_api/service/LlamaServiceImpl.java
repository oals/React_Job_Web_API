package com.example.jobx_api.service;

import com.example.jobx_api.dto.JobQuestionDTO;
import com.example.jobx_api.dto.JobQuestionRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LlamaServiceImpl implements LlamaService {

    private final RestTemplate restTemplate = new RestTemplate();

    private String setChatRequest(List<JobQuestionDTO> jobTestResult) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < jobTestResult.size(); i++) {
            JobQuestionDTO item = jobTestResult.get(i);

//            if (item.getScore() != null) {
                sb.append(i + 1)
                        .append(". (")
                        .append(item.getScore() == null ? "2" : item.getScore())
                        .append("점) ")
                        .append(item.getQuestion())
                        .append("\n");
//            }
        }

        return sb.toString();
    }


    public String getChatResponse(JobQuestionRequestDto jobQuestionRequestDto) throws IOException {
        String url = "http://localhost:11434/api/generate";

        String system = "당신은 직업 상담 전문가입니다. " +
                "사용자가 제출한 직업 가치관 설문 결과, 나이, MBTI 성격 유형을 종합적으로 분석하여 " +
                "가장 적합한 직업 하나를 반드시 추천해야 합니다.\n\n" +

                "다음 형식으로만 간결하게 답변해 주세요. 불필요한 설명이나 서론, 마크다운 문법(예: ###, *, -, 숫자 리스트 등)은 절대 포함하지 마세요.\n\n" +

                "[정확히 한국어로 아래 형식을 따르세요]\n" +
                "직업명: (직업명)\n" +
                "직업 설명: (간단한 설명 100자)\n" +
                "필요한 기술 1: (기술 1)\n" +
                "필요한 기술 2: (기술 2)\n" +
                "필요한 기술 3: (기술 3)\n" +
                "유사 직업 1: (직업명)\n" +
                "유사 직업 1 간단한 소개:  (간단한 소개 20자)\n" +
                "유사 직업 2: (직업명)\n" +
                "유사 직업 2 간단한 소개:  (간단한 소개 20자)\n" +
                "유사 직업 3: (직업명)\n" +
                "유사 직업 3 간단한 소개:  (간단한 소개 20자)\n\n" +

                "모든 내용은 무조건 **한국어**로만 작성해야 하며, 영어 또는 외국어가 포함 되어서는 안 됩니다. \n" +
                "또한, 반드시 사용자의 나이(" + jobQuestionRequestDto.getMemberBirth() + ")와 MBTI(" + jobQuestionRequestDto.getMemberMbti() + ")를 고려하여 직업을 추천해야 하며, 해당 정보와 충돌되는 조건이나 예외 사항을 언급하지 마세요.\n" +
                "답변은 확신 있는 어조로 작성하고, 절대 모호하거나 조건부 문장을 포함하지 마세요.";


        String prompt = setChatRequest(jobQuestionRequestDto.getJobTestResult());

        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama3.1:8b-instruct-q8_0");
        request.put("system", system);
        request.put("prompt", prompt);
        request.put("stream", false);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();

    }
}
