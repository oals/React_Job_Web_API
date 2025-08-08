package com.example.jobx_api.service;

import com.example.jobx_api.dto.JobQuestionDTO;
import com.example.jobx_api.dto.JobQuestionRequestDto;
import com.example.jobx_api.dto.MemberDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LlamaServiceImpl implements LlamaService {


    @Value("${llama.host.url}")
    private String llamaHostUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final MemberService memberService;

    private String setChatRequest(List<JobQuestionDTO> jobTestResult) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < jobTestResult.size(); i++) {
            JobQuestionDTO item = jobTestResult.get(i);

            if (item.getScore() != null) {
                sb.append(i + 1)
                        .append(". (")
                        .append(item.getScore() == null ? "2" : item.getScore())
                        .append("점) ")
                        .append(item.getQuestion())
                        .append("\n");
            }
        }
        return sb.toString();
    }

    public String getRecommendJob(JobQuestionRequestDto jobQuestionRequestDto) throws IOException {

        MemberDto memberDto = memberService.memberInfo(jobQuestionRequestDto.getMemberEmail());

        String url = llamaHostUrl + "/api/generate";

        String testTypeName= "";

        if (jobQuestionRequestDto.getTestType().equals("1")) {
            testTypeName = "직업 가치관 검사";
        } else if (jobQuestionRequestDto.getTestType().equals("2")) {
            testTypeName = "직업 흥미 검사";
        } else {
            testTypeName = "진로 심리 검사";
        }

        String system = "당신은" + testTypeName +" 상담 전문가입니다. " +
                "사용자가 제출한 직업 가치관 설문 결과, 나이, MBTI 성격 유형을 종합적으로 분석하여 " +
                "가장 적합한 직업 하나를 반드시 추천해야 합니다.\n\n" +

                "다음 형식으로만 간결하게 답변해 주세요. 불필요한 설명이나 서론, 마크다운 문법(예: ###, *, -, 숫자 리스트 등)은 절대 포함하지 마세요.\n" +
                "정확히 한국어로 아래 형식을 따르세요\n" +
                "아래의 항목 외에 다른 답변이 포함 되지 않게 하세요\n" +

                "-------------------------------------------\n" +
                "직업명: (직업명)\n" +
                "직업 카테고리: (직업 카테고리)\n" +
                "직업 설명: (최소 250자 최대 300자)\n" +
                "직업 평균 연봉: (예시 : 3,000만원) \n" +
                "직업 필요 역량: (예시 : 분석적 사고, 문제 해결) \n" +
                "직업 연령대: (예시 : 20대 ~ 30대 많음) \n" +
                "직업 직업 전망: (예시 : 안좋음, 보통, 밝음) \n" +
                "직업 아이콘 (부트스트랩) : (예시 : bi bi-clipboard-data) \n" +
                "우대 학과 ( , 로 구분): (에시 : 경영학, 금융학)\n" +
                "우대 자격증 ( , 로 구분): (에시 : CFA, FRM)\n" +
                "필요한 기술 1: (기술 1)\n" +
                "필요한 기술 2: (기술 2)\n" +
                "필요한 기술 3: (기술 3)\n" +
                "-------------------------------------------\n" +

                "위 항목 외에 다른 답변이 포함 되지 않게 하세요\n" +
                "위 형식으로만 간결하게 답변해 주세요. 불필요한 설명이나 서론, 마크다운 문법(예: ###, *, -, 숫자 리스트 등)은 절대 포함하지 마세요.\n" +
                "모든 내용은 무조건 **한국어**로만 작성해야 하며, 영어 또는 외국어가 포함 되어서는 안 됩니다. \n" +
                "또한, 반드시 사용자의 나이(" + memberDto.getMemberBirth() + ")와 MBTI(" + memberDto.getMemberMbti() + ") " +
                "그리고 사용자의 관심분야(" + memberDto.getMemberInterestField() + ")와 희망연봉(" + memberDto.getMemberDesiredSalary() + ") " +
                "그리고 사용자의 성별(" + memberDto.getMemberGender() + ")" +
                "을 고려하여 직업을 추천해야 하며, 해당 정보와 충돌되는 조건이나 예외 사항을 언급하지 마세요.\n" +
                "답변은 확신 있는 어조로 작성하고, 절대 모호하거나 조건부 문장을 포함하지 마세요.";


        String prompt = setChatRequest(jobQuestionRequestDto.getJobTestResult());

        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama3.1:8b-instruct-q8_0");
        request.put("system", system);
        request.put("prompt", prompt);
        request.put("stream", false);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        String jsonBody = response.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonBody);
        String onlyResponse = rootNode.get("response").asText();

        return onlyResponse;

    }
}
