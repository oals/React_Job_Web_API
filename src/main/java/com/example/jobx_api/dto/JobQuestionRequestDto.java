package com.example.jobx_api.dto;

import lombok.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobQuestionRequestDto {

    private String memberEmail;

    private String testType;

    private List<JobQuestionDTO> jobTestResult;
}
